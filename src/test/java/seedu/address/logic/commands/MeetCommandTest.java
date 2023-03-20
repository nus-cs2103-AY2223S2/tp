package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.LocationUtil;
import seedu.address.model.person.ContactIndex;

class MeetCommandTest {

    // EMPTY TIMETABLE standard message
    private static final String FULL_DAY_PERIOD = "Start: 8 AM  End: 10 PM";
    private static final String EMPTY_TIMETABLE_MESSAGE = "MONDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "TUESDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "WEDNESDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "THURSDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "FRIDAY"
        + "\n" + FULL_DAY_PERIOD;

    // 6 is EDWARD and 8 is GEORGE, both in Kent Ridge
    private static final Set<ContactIndex> INDICES =
            Set.of(new ContactIndex(6), new ContactIndex(8));
    private static final MeetCommand MEET_COMMAND =
            new MeetCommand(INDICES, LocationUtil.MEET_LOCATIONS, 3);
    private static final MeetCommand STUDY_COMMAND =
            new MeetCommand(INDICES, LocationUtil.STUDY_LOCATIONS, 2);
    private static final MeetCommand EAT_COMMAND =
            new MeetCommand(INDICES, LocationUtil.EAT_LOCATIONS, 3);

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void execute() throws CommandException {
        String expectedMessage;
        expectedMessage = MeetCommand.MESSAGE_SUCCESS
            + "\n" + EMPTY_TIMETABLE_MESSAGE
            + "\n\n" + "NUS Medical Library"
            + "\n" + "NUS Science Library"
            + "\n" + "Frontier";
        assertCommandSuccess(MEET_COMMAND, model, expectedMessage, expectedModel);
        expectedMessage = MeetCommand.MESSAGE_SUCCESS
            + "\n" + EMPTY_TIMETABLE_MESSAGE
            + "\n\n" + "NUS Medical Library"
            + "\n" + "NUS Science Library";
        assertCommandSuccess(STUDY_COMMAND, model, expectedMessage, expectedModel);
        expectedMessage = MeetCommand.MESSAGE_SUCCESS
            + "\n" + EMPTY_TIMETABLE_MESSAGE
            + "\n\n" + "Frontier"
            + "\n" + "Prince Georges Park"
            + "\n" + "The Terrace";
        assertCommandSuccess(EAT_COMMAND, model, expectedMessage, expectedModel);
    }
}
