package seedu.quickcontacts.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_MEETINGS_ALL_LISTED_OVERVIEW;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.meeting.MeetingContainsNamesPredicate;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindMeetingCommandTest {
    private final Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBook(), new UserPrefs());

    @Test
    public void equals() {
        MeetingContainsNamesPredicate firstPredicate =
            new MeetingContainsNamesPredicate(Collections.singletonList("first"));
        MeetingContainsNamesPredicate secondPredicate =
            new MeetingContainsNamesPredicate(Collections.singletonList("second"));

        FindMeetingCommand findFirstCommand = new FindMeetingCommand(firstPredicate);
        FindMeetingCommand findSecondCommand = new FindMeetingCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindMeetingCommand findFirstCommandCopy = new FindMeetingCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_allMeetingsListed() {
        MeetingContainsNamesPredicate predicate = preparePredicate("");
        FindMeetingCommand command = new FindMeetingCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, MESSAGE_MEETINGS_ALL_LISTED_OVERVIEW, expectedModel);
        assertEquals(3, model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        String inputString = "Alice Carl";
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, "Alice, Carl");
        MeetingContainsNamesPredicate predicate = preparePredicate(inputString);
        FindMeetingCommand command = new FindMeetingCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(3, model.getFilteredMeetingList().size());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private MeetingContainsNamesPredicate preparePredicate(String userInput) {
        if (userInput.isEmpty()) {
            return new MeetingContainsNamesPredicate();
        }
        return new MeetingContainsNamesPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
