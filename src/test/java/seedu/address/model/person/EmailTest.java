package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(Date.isValidDate("@example.com")); // missing local part
        assertFalse(Date.isValidDate("peterjackexample.com")); // missing '@' symbol
        assertFalse(Date.isValidDate("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Date.isValidDate("peterjack@-")); // invalid domain name
        assertFalse(Date.isValidDate("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Date.isValidDate("peter jack@example.com")); // spaces in local part
        assertFalse(Date.isValidDate("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Date.isValidDate(" peterjack@example.com")); // leading space
        assertFalse(Date.isValidDate("peterjack@example.com ")); // trailing space
        assertFalse(Date.isValidDate("peterjack@@example.com")); // double '@' symbol
        assertFalse(Date.isValidDate("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Date.isValidDate("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Date.isValidDate("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Date.isValidDate("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Date.isValidDate("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Date.isValidDate("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Date.isValidDate("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Date.isValidDate("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Date.isValidDate("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Date.isValidDate("peterjack@example.c")); // top level domain has less than two chars

        // valid date
        assertTrue(Date.isValidDate("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Date.isValidDate("PeterJack.1190@example.com")); // period in local part
        assertTrue(Date.isValidDate("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Date.isValidDate("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Date.isValidDate("a@bc")); // minimal
        assertTrue(Date.isValidDate("test@localhost")); // alphabets only
        assertTrue(Date.isValidDate("123@145")); // numeric local part and domain name
        assertTrue(Date.isValidDate("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Date.isValidDate("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Date.isValidDate("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Date.isValidDate("e1234567@u.nus.edu")); // more than one period in domain
    }
}
