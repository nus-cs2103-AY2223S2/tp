package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateAndTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateAndTime(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new DateAndTime(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> DateAndTime.isValidDateAndTime(null));

        // blank email
        assertFalse(DateAndTime.isValidDateAndTime("")); // empty string
        assertFalse(DateAndTime.isValidDateAndTime(" ")); // spaces only

        // missing parts
        assertFalse(DateAndTime.isValidDateAndTime("@example.com")); // missing local part
        assertFalse(DateAndTime.isValidDateAndTime("peterjackexample.com")); // missing '@' symbol
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@-")); // invalid domain name
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(DateAndTime.isValidDateAndTime("peter jack@example.com")); // spaces in local part
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(DateAndTime.isValidDateAndTime(" peterjack@example.com")); // leading space
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@example.com ")); // trailing space
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@@example.com")); // double '@' symbol
        assertFalse(DateAndTime.isValidDateAndTime("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(DateAndTime.isValidDateAndTime("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(DateAndTime.isValidDateAndTime("peterjack-@example.com")); // local part ends with a hyphen
        // local part has two consecutive periods
        assertFalse(DateAndTime.isValidDateAndTime("peter..jack@example.com"));
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@.example.com")); // domain name starts with a period
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@example.com.")); // domain name ends with a period
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(DateAndTime.isValidDateAndTime("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(DateAndTime.isValidDateAndTime("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(DateAndTime.isValidDateAndTime("PeterJack.1190@example.com")); // period in local part
        assertTrue(DateAndTime.isValidDateAndTime("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(DateAndTime.isValidDateAndTime("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(DateAndTime.isValidDateAndTime("a@bc")); // minimal
        assertTrue(DateAndTime.isValidDateAndTime("test@localhost")); // alphabets only
        assertTrue(DateAndTime.isValidDateAndTime("123@145")); // numeric local part and domain name
        // mixture of alphanumeric and special characters
        assertTrue(DateAndTime.isValidDateAndTime("a1+be.d@example1.com"));
        assertTrue(DateAndTime.isValidDateAndTime("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(DateAndTime.isValidDateAndTime("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(DateAndTime.isValidDateAndTime("e1234567@u.nus.edu")); // more than one period in domain
    }
}
