package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
class GenderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "alien";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null email
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank gender
        assertFalse(Gender.isValidGender(" ")); // spaces only

        // invalid gender
        assertFalse(Gender.isValidGender("alien")); // invalid gender
        assertFalse(Gender.isValidGender("male ")); // trailing space
        assertFalse(Gender.isValidGender("fe_male ")); // underscore in gender
        assertFalse(Gender.isValidGender(" DNS")); // leading space in gender
        assertFalse(Gender.isValidGender("ma-le")); // hyphen in gender
        assertFalse(Gender.isValidGender("MALEs")); // plural gender

        // valid email
        assertTrue(Gender.isValidGender("MALE")); // uppercase male
        assertTrue(Gender.isValidGender("FEMALE")); // uppercase female
        assertTrue(Gender.isValidGender("DNS")); // uppercase dns
        assertTrue(Gender.isValidGender("male")); // lowercase male
        assertTrue(Gender.isValidGender("female")); // lowercase female
        assertTrue(Gender.isValidGender("dns")); // lowercase dns
        assertTrue(Gender.isValidGender("MaLe")); // mixed case male
        assertTrue(Gender.isValidGender("FeMaLe")); // mixed case female
        assertTrue(Gender.isValidGender("dNs")); // mixed case dns
    }
}
