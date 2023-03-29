package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.dengue.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, personToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
    //        expectedModel.deletePerson(personToDelete);
    //        showNoPerson(expectedModel);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of Dengue Hotspot Tracker list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getDengueHotspotTracker().getPersonList().size());
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
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
