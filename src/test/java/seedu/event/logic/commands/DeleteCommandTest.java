
package seedu.event.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.event.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.event.testutil.TypicalContacts.getTypicalContactList;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.event.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.event.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.event.commons.core.Messages;
import seedu.event.commons.core.index.Index;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;
import seedu.event.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of event book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_EVENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
