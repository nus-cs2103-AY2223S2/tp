
package seedu.event.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.event.model.event.Rate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RateCommand}.
 */
public class RateCommandTest {

    private Model model = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Event event = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Rate rate = event.getRate();
        RateCommand rateCommand = new RateCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(RateCommand.MESSAGE_SUCCESS, rate);

        ModelManager expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
        expectedModel.getRate(event);

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        RateCommand rateCommand = new RateCommand(outOfBoundIndex);

        assertCommandFailure(rateCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(INDEX_FIRST_EVENT);
        RateCommand rateSecondCommand = new RateCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(rateFirstCommand.equals(rateFirstCommand));

        // same values -> returns true
        RateCommand rateFirstCommandCopy = new RateCommand(INDEX_FIRST_EVENT);
        assertTrue(rateFirstCommand.equals(rateFirstCommandCopy));

        // different types -> returns false
        assertFalse(rateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rateFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(rateFirstCommand.equals(rateSecondCommand));
    }
}
