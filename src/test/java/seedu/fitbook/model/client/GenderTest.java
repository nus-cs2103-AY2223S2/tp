package seedu.fitbook.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GenderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid genders
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender("y")); // not recognised abbreviation

        // valid genders
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("f")); // small character 'f'
        assertTrue(Gender.isValidGender("F")); // big character 'F'
    }
    @Test
    public void test_equalsSymmetric() {
        Gender genderA = new Gender("m");
        Gender genderB = new Gender("m");
        assertTrue(genderA.equals(genderB) && genderB.equals(genderA));
        assertTrue(genderA.hashCode() == genderB.hashCode());
    }
}
