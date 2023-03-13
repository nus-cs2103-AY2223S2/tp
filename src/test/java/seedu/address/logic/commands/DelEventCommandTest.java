package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DelEventCommand}.
 */
public class DelEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DelEventCommand delEventCommand = new DelEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(DelEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        expectedModel.deleteEventFromPersonList(eventToDelete);

        assertCommandSuccess(delEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DelEventCommand delEventCommand = new DelEventCommand(outOfBoundIndex);

        assertCommandFailure(delEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DelEventCommand delEventCommand = new DelEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(DelEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        expectedModel.deleteEventFromPersonList(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(delEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        DelEventCommand delEventCommand = new DelEventCommand(outOfBoundIndex);

        assertCommandFailure(delEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DelEventCommand delEventFirstCommand = new DelEventCommand(INDEX_FIRST_EVENT);
        DelEventCommand delEventSecondCommand = new DelEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(delEventFirstCommand.equals(delEventFirstCommand));

        // same values -> returns true
        DelEventCommand delEventFirstCommandCopy = new DelEventCommand(INDEX_FIRST_EVENT);
        assertTrue(delEventFirstCommand.equals(delEventFirstCommandCopy));

        // different types -> returns false
        assertFalse(delEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(delEventFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(delEventFirstCommand.equals(delEventSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no event.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
