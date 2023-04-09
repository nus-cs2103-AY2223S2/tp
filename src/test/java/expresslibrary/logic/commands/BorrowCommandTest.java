package expresslibrary.logic.commands;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static expresslibrary.logic.commands.CommandTestUtil.*;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        String expectedMessage = String.format(BorrowCommand.MESSAGE_BORROW_SUCCESS, personToLend, bookToBorrow);

        ModelManager expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        Person origPerson = expectedModel.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Book origBook = expectedModel.getFilteredBookList().get(INDEX_SECOND.getZeroBased());

        Person editedPerson = new Person(
                origPerson.getName(), origPerson.getPhone(), origPerson.getEmail(),
                origPerson.getBooks(), origPerson.getTags());
        editedPerson.borrowBook(origBook);
        origBook.loanBookTo(editedPerson, LocalDate.now(), dueDate);

        expectedModel.setPerson(origPerson, editedPerson);
        expectedModel.setBook(origBook, origBook);

        assertCommandSuccess(borrowCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST, false);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of express library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpressLibrary().getPersonList().size());

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(INDEX_FIRST, false);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(INDEX_SECOND, false);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(INDEX_FIRST, false);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

}
