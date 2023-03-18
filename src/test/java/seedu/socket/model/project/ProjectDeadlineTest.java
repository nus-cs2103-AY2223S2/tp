package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.model.project.ProjectDeadline.DATE_TIME_FORMAT;
import static seedu.socket.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ProjectDeadlineTest {
    private static final ProjectDeadline ALPHA_DEADLINE = new ProjectDeadline(VALID_PROJECT_DEADLINE_ALPHA);
    private static final ProjectDeadline BRAVO_DEADLINE = new ProjectDeadline(VALID_PROJECT_DEADLINE_BRAVO);
    private static final LocalDateTime VALID_LOCAL_DATE_TIME_ALPHA = LocalDateTime.parse(VALID_PROJECT_DEADLINE_ALPHA,
        DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectDeadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = " ";
        assertThrows(IllegalArgumentException.class, () -> new ProjectDeadline(invalidDeadline));
    }

    @Test
    public void testToString() {
        assertTrue(VALID_PROJECT_DEADLINE_ALPHA.equals(ALPHA_DEADLINE.toString()));
    }
    @Test
    public void testToLocalDateTime() {
        assertTrue(VALID_LOCAL_DATE_TIME_ALPHA.equals(ALPHA_DEADLINE.toLocalDateTime()));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        ProjectDeadline amyCopy = new ProjectDeadline(VALID_PROJECT_DEADLINE_ALPHA);
        assertTrue(ALPHA_DEADLINE.equals(amyCopy));

        // same object -> returns true
        assertTrue(ALPHA_DEADLINE.equals(ALPHA_DEADLINE));

        // null -> returns false
        assertFalse(ALPHA_DEADLINE.equals(null));

        // different type -> returns false
        assertFalse(ALPHA_DEADLINE.equals(5));

        // different profile -> returns false
        assertFalse(ALPHA_DEADLINE.equals(BRAVO_DEADLINE));
    }
}
