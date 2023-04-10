package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

//@@author hmuumyatmoe-reused
public class TaskDeadlineTest {

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with modifications
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
    //@@author

    //Solution below for isValidTaskDeadline is adapted from AB3
    @Test
    public void isValidTaskDeadline() {
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidDeadline(null));

        // invalid task deadline

        String wrongFormatDate = "2024-01-01";
        assertFalse(TaskDeadline.isValidDeadline(wrongFormatDate)); //deadline is in the wrong format

        String invalidDate = "35/14/2024";
        assertFalse(TaskDeadline.isValidDeadline(invalidDate)); //deadline is an invalid date in the calendar

        String notADate = "Not a Date";
        assertFalse(TaskDeadline.isValidDeadline(notADate)); // deadline is not a date

        // valid task deadline
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);
        String tomorrow = LocalDate.now().plusDays(1).format(dtf);
        String futureDate = LocalDate.now().plusMonths(7).plusDays(21).format(dtf);
        String pastDate = LocalDate.now().minusMonths(7).minusDays(10).format(dtf);
        assertTrue(TaskDeadline.isValidDeadline(todayDate)); // today's date
        assertTrue(TaskDeadline.isValidDeadline(tomorrow)); // tomorrow's date
        assertTrue(TaskDeadline.isValidDeadline(futureDate)); // future date
        assertTrue(TaskDeadline.isValidDeadline(pastDate)); // past date
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
