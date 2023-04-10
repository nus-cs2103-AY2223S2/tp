package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code BorrowCommand}.
 */
public class BorrowCommandTest {

    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToLend = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Book bookToBorrow = model.getFilteredBookList().get(INDEX_SECOND.getZeroBased());

        LocalDate dueDate = LocalDate.now().plusDays(1);
        BorrowCommand borrowCommand = new BorrowCommand(INDEX_FIRST, INDEX_SECOND, dueDate);
        personToLend.borrowBook(bookToBorrow);

        String expectedMessage = String.format(BorrowCommand.MESSAGE_BORROW_SUCCESS, personToLend, bookToBorrow);

        ModelManager expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        Person origPerson = expectedModel.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Book origBook = expectedModel.getFilteredBookList().get(INDEX_SECOND.getZeroBased());

        Person editedPerson = new Person(
                origPerson.getName(), origPerson.getPhone(), origPerson.getEmail(),
                origPerson.getBooks(), origPerson.getTags());
        editedPerson.borrowBook(origBook);

        expectedModel.setPerson(origPerson, editedPerson);

        assertCommandSuccess(borrowCommand, model, expectedMessage, expectedModel);
    }

}
