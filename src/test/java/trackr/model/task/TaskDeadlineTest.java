package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void isValidTaskDeadline() {
        // null task name
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // invalid task deadline
        LocalDate passedDate = LocalDate.parse("2022-01-01");
        assertFalse(TaskDeadline.isValidTaskDeadline(passedDate)); // deadline is before today's date

        // valid task deadline
        LocalDate todayDate = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate farAwayDate = LocalDate.now().plusYears(1).plusMonths(2).plusDays(71);
        assertTrue(TaskDeadline.isValidTaskDeadline(todayDate)); // today's date
        assertTrue(TaskDeadline.isValidTaskDeadline(tomorrow)); // tomorrow's date
        assertTrue(TaskDeadline.isValidTaskDeadline(farAwayDate)); // very far away date
    }
}
