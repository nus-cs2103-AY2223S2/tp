
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
import seedu.event.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
public class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToUnmark = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event unmarkedEvent = new EventBuilder(eventToUnmark).build();
        unmarkedEvent.mark();
        unmarkedEvent.unmark();
        eventToUnmark.unmark();
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_EVENT_SUCCESS, eventToUnmark);

        ModelManager expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
        expectedModel.unmarkEvent(eventToUnmark);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToUnmark = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event unmarkedEvent = new EventBuilder(eventToUnmark).build();
        unmarkedEvent.mark();
        unmarkedEvent.unmark();
        eventToUnmark.unmark();
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_EVENT_SUCCESS, eventToUnmark);

        ModelManager expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
        expectedModel.markEvent(eventToUnmark);

        showNoEvent(expectedModel);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of event book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST_EVENT);
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST_EVENT);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
