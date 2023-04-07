package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(),
            getTypicalEventCatalogue(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete), ResultType.HOME);

        ModelManager expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        // After Deleting Internship , it is important to delete all the events associated.
        expectedModel.updateFilteredEventList(new EventByInternship(internshipToDelete));
        List<Event> eventListToDelete = expectedModel.getFilteredEventList();
        // Necessary to create an unmodifiable array , as eventListToDelete() is getting updated with deletion
        Event[] eventListToDeleteArray = eventListToDelete.toArray(new Event[eventListToDelete.size()]);

        for (int i = 0; i < eventListToDeleteArray.length; i++) {
            Event e = eventListToDeleteArray[i];
            // Delete the Events associated with that internship
            expectedModel.deleteEvent(e);
        }

        assertCommandSuccess(deleteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);

        CommandResult expectedCommandResult = new CommandResult(String.format(
                DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete), ResultType.HOME);


        Model expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        // After Deleting Internship , it is important to delete all the events associated.
        expectedModel.updateFilteredEventList(new EventByInternship(internshipToDelete));
        List<Event> eventListToDelete = expectedModel.getFilteredEventList();
        // Necessary to create an unmodifiable array , as eventListToDelete() is getting updated with deletion
        Event[] eventListToDeleteArray = eventListToDelete.toArray(new Event[eventListToDelete.size()]);

        for (int i = 0; i < eventListToDeleteArray.length; i++) {
            Event e = eventListToDeleteArray[i];
            // Delete the Events associated with that internship
            expectedModel.deleteEvent(e);
        }
        showNoInternship(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipCatalogue().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_INTERNSHIP);
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
    private void showNoInternship(Model model) {
        model.updateFilteredInternshipList(p -> false);

        assertTrue(model.getFilteredInternshipList().isEmpty());
    }
}
