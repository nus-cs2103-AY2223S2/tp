package seedu.quickcontacts.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.quickcontacts.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {
    public static final Meeting MEETING_A = new MeetingBuilder().withTitle("Dinner with Alice")
            .withDateTime("01/02/2023 19:00").withAttendees(TypicalPersons.ALICE).withLocation("NUS")
            .withDescription("Weekly catchup").build();
    public static final Meeting MEETING_B = new MeetingBuilder().withTitle("Study session with Benson and Carl")
            .withDateTime("02/03/2023 15:00").withAttendees(TypicalPersons.BENSON, TypicalPersons.CARL)
            .withLocation("Central Library").withDescription("Study for finals").build();
    public static final Meeting MEETING_C = new MeetingBuilder().withTitle("Zoom meeting for agenda planning")
            .withDateTime("13/03/2023 12:45").withAttendees(TypicalPersons.ALICE)
            .withLocation("https://us02web.zoom.us/j/99999999999?pwd=ABCdEfGHiJkYkRuYW5WTLmNopQrSt12")
            .withDescription("Plan for project work").build();
    public static final Meeting UNDONE_MEETING = new MeetingBuilder().withIsDone(false).build();
    public static final Meeting DONE_MEETING = new MeetingBuilder().withIsDone(true).build();
    public static final Meeting DONE_MEETING2 = new MeetingBuilder().withTitle("A").withIsDone(true).build();

    /**
     * Private constructor to prevent initialisation.
     */
    private TypicalMeetings() {}

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING_A, MEETING_B, MEETING_C));
    }
}
