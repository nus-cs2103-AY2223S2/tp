package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingStartDatePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindMeetingCommandTest {

    private final FindMeetingCommand standardCommand = new FindMeetingCommand(VALID_MEETING_START_DATE_AMY);
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void equals() {
        FindMeetingCommand commandWithSameValues = new FindMeetingCommand(VALID_MEETING_START_DATE_AMY);
        FindMeetingCommand diffValue = new FindMeetingCommand(LocalDate.of(2022, 3, 5));
        assertTrue(standardCommand.equals(commandWithSameValues));
        assertTrue(standardCommand.equals(new FindMeetingCommand(VALID_MEETING_START_DATE_BOB)));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null object -> returns false
        assertFalse(standardCommand.equals(null));

        // Different command -> return false
        assertFalse(standardCommand.equals(new FindCommand(new NameContainsKeywordsPredicate(
            Collections.singletonList("Test")))));

        // Different inputs -> return false
        assertFalse(standardCommand.equals(diffValue));
    }


    @Test
    public void execute_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        FindMeetingCommand cmd = new FindMeetingCommand(LocalDate.of(2023, 3, 26));
        MeetingStartDatePredicate p = new MeetingStartDatePredicate(LocalDate.of(2023, 3, 26));
        expectedModel.updateFilteredMeetingList(p);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }


    @Test
    public void execute_multiplePersonFound() throws CommandException {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 7);
        FindMeetingCommand cmd = new FindMeetingCommand(LocalDate.of(2023, 3, 25));
        MeetingStartDatePredicate p = new MeetingStartDatePredicate(LocalDate.of(2023, 3, 25));
        expectedModel.updateFilteredMeetingList(p);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void invalidPersonIndexTest() {
        int sampleIndex = 15;
        FindMeetingCommand sampleFindMeetingCommand = new FindMeetingCommand(Index.fromZeroBased(sampleIndex));

        assertThrows(CommandException.class, () -> sampleFindMeetingCommand.execute(model));
    }
}

