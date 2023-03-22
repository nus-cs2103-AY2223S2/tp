package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.CARL;
import static seedu.address.testutil.TypicalEvents.ELLE;
import static seedu.address.testutil.TypicalEvents.FIONA;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalTimes.CLOCK_FIXED_AT_TIME_NOW;
import static seedu.address.testutil.TypicalTimes.TYPICAL_DAYS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.StartTimeWithinDaysPredicate;
import seedu.address.testutil.TypicalContacts;

/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {
    private Model model = new ModelManager(getTypicalEventBook(),
            TypicalContacts.getTypicalContactList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEventBook(),
            TypicalContacts.getTypicalContactList(), new UserPrefs());

    @Test
    public void equals() {
        StartTimeWithinDaysPredicate firstPredicate =
                new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, 3);
        StartTimeWithinDaysPredicate secondPredicate =
                new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, 4);

        RemindCommand remindFirstCommand = new RemindCommand(firstPredicate);
        RemindCommand remindSecondCommand = new RemindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values -> returns true
        RemindCommand remindFirstCommandCopy = new RemindCommand(firstPredicate);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }

    @Test
    public void execute_multipleEventsWithinDays_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        StartTimeWithinDaysPredicate predicate = preparePredicate("3");
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredEventList());
    }

    /**
     * Parses {@code userInput} into a {@code StartTimeWithinDaysPredicate}.
     */
    private StartTimeWithinDaysPredicate preparePredicate(String userInput) {
        return new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, TYPICAL_DAYS);
    }
}
