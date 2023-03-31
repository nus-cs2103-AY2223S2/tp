package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;

import static seedu.address.logic.commands.CommandTestUtil.*;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingStartDatePredicate;
import seedu.address.model.person.MeetingWithPerson;
import seedu.address.model.person.PersonLivesInRegionPredicate;
import seedu.address.model.person.Region.Regions;

public class FindMeetingCommandTest {

    private class Hi {

    }
    private final FindMeetingCommand standardCommand = new FindMeetingCommand(VALID_MEETING_START_AMY);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindMeetingCommand commandWithSameValues = new FindMeetingCommand(VALID_MEETING_START_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null object -> returns false
        assertFalse(standardCommand.equals(null));

        // Different command -> return false
        assertFalse(standardCommand.equals(new Hi()));

        // Different inputs -> return false
        assertFalse(standardCommand.equals(new FindMeetingCommand(VALID_MEETING_START_BOB)));

    }


    @Test
    public void execute_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        FindMeetingCommand cmd = new FindMeetingCommand(LocalDateTime.of(2023, 3, 26, 19, 25));
        MeetingStartDatePredicate p = new MeetingStartDatePredicate(LocalDateTime.of(2023, 3, 26, 19, 25));
        expectedModel.updateFilteredMeetingList(p);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }


}

