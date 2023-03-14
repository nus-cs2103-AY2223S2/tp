package seedu.careflow.model.person;

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
        assertFalse(Gender.isValidGender("fm"));
        assertFalse(Gender.isValidGender("no idea"));

        // valid gender
        assertTrue(Gender.isValidGender("f"));
        assertTrue(Gender.isValidGender("feMale"));
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("Male"));
    }
}
