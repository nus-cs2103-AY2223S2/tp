package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTagCommand}.
 */

public class DeleteTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Tag tagToDelete = new Tag("friends");

    /*@Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDeleteFrom = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagToDelete);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS, personToDeleteFrom);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTag(personToDeleteFrom, tagToDelete);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }*/

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, tagToDelete);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /*@Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDeleteFrom = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagToDelete);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS, personToDeleteFrom);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTag(personToDeleteFrom, tagToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }*/

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, tagToDelete);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteTagFirstCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagToDelete);
        DeleteTagCommand deleteTagSecondCommand = new DeleteTagCommand(INDEX_SECOND_PERSON, tagToDelete);

        // same object -> returns true
        assertTrue(deleteTagFirstCommand.equals(deleteTagFirstCommand));

        // same values -> returns true
        DeleteTagCommand deleteTagFirstCommandCopy = new DeleteTagCommand(INDEX_FIRST_PERSON, tagToDelete);
        assertTrue(deleteTagFirstCommand.equals(deleteTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteTagFirstCommand.equals(deleteTagSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
