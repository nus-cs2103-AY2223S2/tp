package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
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
    public void isValidDrugAllergy() {
        // null
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank description
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only

        // invalid gender
        assertFalse(Gender.isValidGender("-")); // with special character
        assertFalse(Gender.isValidGender("fm"));
        assertFalse(Gender.isValidGender("no idea"));

        // valid gender
        assertTrue(Gender.isValidGender("f"));
        assertTrue(Gender.isValidGender("feMale"));
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("Male"));
    }

    @Test
    public void equals() {
        Gender gender = new Gender("female");
        // null -> returns false
        assertFalse(gender.equals(null));

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // same values -> returns true
        assertTrue(gender.equals(new Gender("female")));

        // different values -> return false
        assertFalse(gender.equals("male"));
    }
}
