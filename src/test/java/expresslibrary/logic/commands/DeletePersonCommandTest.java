package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.logic.commands.CommandTestUtil.showPersonAtIndex;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Person;




/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON, false);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deletePersonWithBorrowedBooks_throwsCommandException() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Book> borrowedBooks = new HashSet<>(personToDelete.getBooks());
        borrowedBooks.add(new Book(new Title("Book 3"), new Author("Author 3"), new Isbn("1234567890")));
        Person personWithBorrowedBooks = new Person(personToDelete.getName(), personToDelete.getPhone(),
                personToDelete.getEmail(), borrowedBooks, personToDelete.getTags());
        model.setPerson(personToDelete, personWithBorrowedBooks);

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON, false);
        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_DELETE_PERSON);
    }

    @Test
    public void execute_borrowBookNotInList_throwsCommandException() {

    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON, false);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of express library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpressLibrary().getPersonList().size());

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(INDEX_FIRST_PERSON, false);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(INDEX_SECOND_PERSON, false);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(INDEX_FIRST_PERSON, false);
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
