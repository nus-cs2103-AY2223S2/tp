package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.student.Attendance;

public class AttendanceTest {

    @Test
    public void constructor_invalidAttendance_throwsIllegalArgumentException() {
        String invalidAttendance = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendance));
    }

    @Test
    public void isValidAttendance() {

        // invalid attendance
        assertFalse(Attendance.isValidAttendance("")); // empty string
        assertFalse(Attendance.isValidAttendance(" ")); // spaces only
        assertFalse(Attendance.isValidAttendance("^")); // only non-alphanumeric characters
        assertFalse(Attendance.isValidAttendance("*")); // contains non-alphanumeric characters
        assertFalse(Attendance.isValidAttendance("35/01/2022")); // contains invalid dates
        assertFalse(Attendance.isValidAttendance("01/20/2022")); // contains invalid months

        // valid attendance
        assertTrue(Attendance.isValidAttendance("12/12/2020")); // valid date, month and year
        assertTrue(Attendance.isValidAttendance("T")); // present
        assertTrue(Attendance.isValidAttendance("F")); // absent
    }
}
