package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_ALL_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.MeetingContainsNamesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
        assertCommandSuccess(command, model, MESSAGE_MEETINGS_ALL_LISTED_OVERVIEW, expectedModel);
        assertEquals(3, model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        String inputString = "Alice Carl";
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, "Alice, Carl");
        MeetingContainsNamesPredicate predicate = preparePredicate(inputString);
        FindMeetingCommand command = new FindMeetingCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
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
