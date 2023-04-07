package trackr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> PersonEmail.isValidPersonEmail(null));

        // blank email
        assertFalse(PersonEmail.isValidPersonEmail("")); // empty string
        assertFalse(PersonEmail.isValidPersonEmail(" ")); // spaces only

        // missing parts
        assertFalse(PersonEmail.isValidPersonEmail("@example.com")); // missing local part
        assertFalse(PersonEmail.isValidPersonEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@-")); // invalid domain name
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(PersonEmail.isValidPersonEmail("peter jack@example.com")); // spaces in local part
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(PersonEmail.isValidPersonEmail(" peterjack@example.com")); // leading space
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@example.com ")); // trailing space
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(PersonEmail.isValidPersonEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(PersonEmail.isValidPersonEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(PersonEmail.isValidPersonEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(PersonEmail.isValidPersonEmail("pet..jack@ex.com")); // local part has two consecutive periods
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(PersonEmail.isValidPersonEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(PersonEmail.isValidPersonEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(PersonEmail.isValidPersonEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(PersonEmail.isValidPersonEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(PersonEmail.isValidPersonEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(PersonEmail.isValidPersonEmail("a@bc")); // minimal
        assertTrue(PersonEmail.isValidPersonEmail("test@localhost")); // alphabets only
        assertTrue(PersonEmail.isValidPersonEmail("123@145")); // numeric local part and domain name
        assertTrue(PersonEmail.isValidPersonEmail("a1+be.d@ex1.com")); // mixture of alphanumeric and special characters
        assertTrue(PersonEmail.isValidPersonEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(PersonEmail.isValidPersonEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(PersonEmail.isValidPersonEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        PersonEmail personEmail = new PersonEmail("a@bc");
        PersonEmail differentPersonEmail = new PersonEmail("test@localhost");

        assertTrue(personEmail.equals(personEmail)); //same object
        assertTrue(personEmail.equals(new PersonEmail("a@bc"))); //same email

        assertFalse(personEmail.equals(null)); //null
        assertFalse(personEmail.equals(differentPersonEmail)); //different email
        assertFalse(personEmail.equals(1)); //different type
    }

    @Test
    public void hashCode_success() {
        String email = "test@test";
        assertEquals(email.hashCode(), new PersonEmail(email).hashCode());
    }

}
