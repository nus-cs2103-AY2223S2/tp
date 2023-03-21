package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.ART;
import static ezschedule.testutil.TypicalEvents.BOAT;
import static ezschedule.testutil.TypicalEvents.CARNIVAL;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.EventContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void equals() {
        EventContainsKeywordsPredicate firstPredicate =
                new EventContainsKeywordsPredicate(Collections.singletonList("first"));
        EventContainsKeywordsPredicate secondPredicate =
                new EventContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventContainsKeywordsPredicate predicate = preparePredicate("Art Boat Carnival");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ART, BOAT, CARNIVAL), model.getFilteredEventList());
    }

    /**
     * Parses {@code userInput} into a {@code EventContainsKeywordsPredicate}.
     */
    private EventContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EventContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
