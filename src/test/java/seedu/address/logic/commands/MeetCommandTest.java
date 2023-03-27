package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Set;

import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.util.LocationDataUtil;
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
            new MeetCommand(INDICES, LocationDataUtil.MEET_LOCATIONS, 3);
    private static final MeetCommand STUDY_COMMAND =
            new MeetCommand(INDICES, LocationDataUtil.STUDY_LOCATIONS, 2);
    private static final MeetCommand EAT_COMMAND =
            new MeetCommand(INDICES, LocationDataUtil.EAT_LOCATIONS, 3);

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
}
