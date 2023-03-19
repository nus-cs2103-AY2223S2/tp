package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeSlot(null));
    }

    @Test
    public void constructor_invalidTimeSlot_throwsIllegalArgumentException() {
        String invalidTimeSlot = "";
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(invalidTimeSlot));
    }

    @Test
    public void isValidTimeSlot() {
        // null timeSlot
        assertThrows(NullPointerException.class, () -> TimeSlot.isValidTimeSlot(null));

        // blank timeSlot
        assertFalse(TimeSlot.isValidTimeSlot("")); // empty string
        assertFalse(TimeSlot.isValidTimeSlot(" ")); // spaces only

        // missing parts
        assertFalse(TimeSlot.isValidTimeSlot("@example.com")); // missing local part
        assertFalse(TimeSlot.isValidTimeSlot("peterjackexample.com")); // missing '@' symbol
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@-")); // invalid domain name
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(TimeSlot.isValidTimeSlot("peter jack@example.com")); // spaces in local part
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(TimeSlot.isValidTimeSlot(" peterjack@example.com")); // leading space
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@example.com ")); // trailing space
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@@example.com")); // double '@' symbol
        assertFalse(TimeSlot.isValidTimeSlot("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(TimeSlot.isValidTimeSlot("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(TimeSlot.isValidTimeSlot("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(TimeSlot.isValidTimeSlot("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@.example.com")); // domain name starts with a period
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@example.com.")); // domain name ends with a period
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(TimeSlot.isValidTimeSlot("peterjack@example.c")); // top level domain has less than two chars

        // valid timeSlot
        assertTrue(TimeSlot.isValidTimeSlot("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(TimeSlot.isValidTimeSlot("PeterJack.1190@example.com")); // period in local part
        assertTrue(TimeSlot.isValidTimeSlot("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(TimeSlot.isValidTimeSlot("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(TimeSlot.isValidTimeSlot("a@bc")); // minimal
        assertTrue(TimeSlot.isValidTimeSlot("test@localhost")); // alphabets only
        assertTrue(TimeSlot.isValidTimeSlot("123@145")); // numeric local part and domain name
        assertTrue(TimeSlot.isValidTimeSlot("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(TimeSlot.isValidTimeSlot("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(TimeSlot.isValidTimeSlot("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(TimeSlot.isValidTimeSlot("e1234567@u.nus.edu")); // more than one period in domain
    }
}
