package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_BRAVO;
import static seedu.socket.model.project.ProjectMeeting.DATE_TIME_FORMAT;
import static seedu.socket.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ProjectMeetingTest {
    private static final ProjectMeeting ALPHA_MEETING = new ProjectMeeting(VALID_PROJECT_MEETING_ALPHA);
    private static final ProjectMeeting BRAVO_MEETING = new ProjectMeeting(VALID_PROJECT_MEETING_BRAVO);
    private static final LocalDateTime VALID_LOCAL_DATE_TIME_ALPHA = LocalDateTime.parse(VALID_PROJECT_MEETING_ALPHA,
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectMeeting(null));
    }

    @Test
    public void constructor_invalidMeeting_throwsIllegalArgumentException() {
        String invalidMeeting = "31/02/2019-2359";
        assertThrows(IllegalArgumentException.class, () -> new ProjectMeeting(invalidMeeting));
    }

    @Test
    public void testToString() {
        assertTrue(VALID_PROJECT_MEETING_ALPHA.equals(ALPHA_MEETING.toString()));
    }
    @Test
    public void testToLocalDateTime() {
        assertTrue(VALID_LOCAL_DATE_TIME_ALPHA.equals(ALPHA_MEETING.toLocalDateTime()));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        ProjectMeeting amyCopy = new ProjectMeeting(VALID_PROJECT_MEETING_ALPHA);
        assertTrue(ALPHA_MEETING.equals(amyCopy));

        // same object -> returns true
        assertTrue(ALPHA_MEETING.equals(ALPHA_MEETING));

        // null -> returns false
        assertFalse(ALPHA_MEETING.equals(null));

        // different type -> returns false
        assertFalse(ALPHA_MEETING.equals(5));

        // different profile -> returns false
        assertFalse(ALPHA_MEETING.equals(BRAVO_MEETING));
    }
}
