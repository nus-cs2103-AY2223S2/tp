package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_wrongFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("2023/01/01"));
    }

    @Test
    public void constructor_dateNotInCalendar_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("35/14/2023"));
    }

    @Test
    public void constructor_notDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("Not a Date"));
    }

    @Test
    public void isValidTaskDeadline() {
        // null task deadline
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // invalid task deadline

        String wrongFormatDate = "2024-01-01";
        assertFalse(TaskDeadline.isValidTaskDeadline(wrongFormatDate)); //deadline is in the wrong format

        String invalidDate = "35/14/2024";
        assertFalse(TaskDeadline.isValidTaskDeadline(invalidDate)); //deadline is an invalid date in the calendar

        String notADate = "Not a Date";
        assertFalse(TaskDeadline.isValidTaskDeadline(notADate)); // deadline is not a date

        // valid task deadline
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);
        String tomorrow = LocalDate.now().plusDays(1).format(dtf);
        String futureDate = LocalDate.now().plusMonths(7).plusDays(21).format(dtf);
        String pastDate = LocalDate.now().minusMonths(7).minusDays(10).format(dtf);
        assertTrue(TaskDeadline.isValidTaskDeadline(todayDate)); // today's date
        assertTrue(TaskDeadline.isValidTaskDeadline(tomorrow)); // tomorrow's date
        assertTrue(TaskDeadline.isValidTaskDeadline(futureDate)); // future date
        assertTrue(TaskDeadline.isValidTaskDeadline(pastDate)); // past date
    }

    @Test
    public void toStringTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String expectedDate = LocalDate.now().format(dtf2);

        assertEquals(expectedDate, new TaskDeadline(todayDate).toString());
    }

    @Test
    public void toJsonString() {
        String expectedDate = "10/10/2023";
        assertEquals(expectedDate, new TaskDeadline(expectedDate).toJsonString());
    }

    @Test
    public void equals() {
        TaskDeadline taskDeadline = new TaskDeadline("01/01/2024");
        TaskDeadline differentDeadline = new TaskDeadline("15/07/2025");

        assertTrue(taskDeadline.equals(taskDeadline)); //same object
        assertTrue(taskDeadline.equals(new TaskDeadline("01/01/2024"))); //same deadline

        assertFalse(taskDeadline.equals(null)); //null
        assertFalse(taskDeadline.equals(differentDeadline)); //different deadlines
        assertFalse(taskDeadline.equals(1)); //different type
    }

}
