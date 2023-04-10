package seedu.connectus.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String s = null;
        assertThrows(NullPointerException.class, () -> new Birthday(s));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));

        String invalidBirthdayTonight = "tonight";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthdayTonight));

        String invalidBirthdayTomorrow = "12/12/2026";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthdayTomorrow));

    }

    @Test
    public void constructor_working_example() {
        String validBirthday = "01/01/2000";
        assertTrue(Birthday.isValidBirthday(validBirthday));

        Birthday bd = new Birthday(validBirthday);
        assertTrue(bd.toString().equals("January 1, 2000"));
    }

    @Test
    public void isUpcoming_working_example() {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(15);
        date = date.withYear(2000);
        String bdStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Birthday bd = new Birthday(bdStr);
        assertTrue(bd.isUpcoming());
    }

    @Test
    public void isUpcoming_working_example2() {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(60);
        date = date.withYear(2000);
        String bdStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Birthday bd = new Birthday(bdStr);
        assertTrue(bd.isUpcoming());
    }
    @Test
    public void isUpcoming_failing_example2() {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(61);
        date = date.withYear(2000);
        String bdStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Birthday bd = new Birthday(bdStr);
        assertFalse(bd.isUpcoming());
    }

    @Test
    public void isUpcoming_working_example3() {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(59).withYear(2000);
        String bdStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Birthday bd = new Birthday(bdStr);
        assertTrue(bd.isUpcoming());
    }

    @Test
    public void isUpcoming_failing_example() {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(-45);
        String bdStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Birthday bd = new Birthday(bdStr);
        assertTrue(!bd.isUpcoming());
    }

}
