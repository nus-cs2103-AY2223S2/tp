package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("e1234567")); // missing '@' symbol
        assertFalse(Email.isValidEmail("e1234567@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@u.nus.edu")); // invalid local name
        assertFalse(Email.isValidEmail("e1234567@u_nus.edu")); // underscore in domain name
        assertFalse(Email.isValidEmail("e1234 56@u.nus.edu")); // spaces in local part
        assertFalse(Email.isValidEmail("e1234567@u.n us.edu")); // spaces in domain name
        assertFalse(Email.isValidEmail(" e1234567@u.nus.edu")); // leading space
        assertFalse(Email.isValidEmail("e1234567@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("e123456@u.nus.edu")); // Less than 7 digits
        assertFalse(Email.isValidEmail("e12345678@u.nus.edu")); // More than 7 digits
        assertFalse(Email.isValidEmail("b12345678@u.nus.edu")); // Local name start with something other than e
        assertFalse(Email.isValidEmail("e1234567@@u.nus.edu")); // double '@' symbol
        assertFalse(Email.isValidEmail("e1234@67@u.nus.edu")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-e1234567@u.nus.edu")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("e123456-@u.nus.edu")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("e12..567@u.nus.edu")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("e1234567@u.nus@edu")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("e1234567@.u.nus.edu")); // domain name starts with a period
        assertFalse(Email.isValidEmail("e1234567@u.nus.edu.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("e1234567@-u.nus.edu")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("e1234567@u.nus.edu-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("e1234567@gmail.com")); // different domain name other than u.nus.edu

        // valid email
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu")); // nus email
        assertTrue(Email.isValidEmail("e0000000@u.nus.edu")); // nus email
    }
}
