package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class SexTest {
    private String male1 = "M";
    private String male2 = "m";
    private String female1 = "F";
    private String female2 = "f";
    private String sampleGender = "Insert student sex here!";
    private String invalidGender1 = ""; // Empty string
    private String invalidGender2 = " "; // Spaces only
    private String invalidGender3 = "^"; // Non-alphanumeric characters
    private String invalidGender4 = "*"; // Non-alphanumeric characters
    private String invalidGender5 = "abc"; // Alphabets
    private String invalidGender6 = "1"; // Mixture of symbols with numbers

    @Test
    public void constructorTest() {
        // invalid inputs
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender1));
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender2));
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender3));
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender4));
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender5));
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidGender6));

        // valid inputs
        assertDoesNotThrow(() -> new Sex(male1));
        assertDoesNotThrow(() -> new Sex(male2));
        assertDoesNotThrow(() -> new Sex(female1));
        assertDoesNotThrow(() -> new Sex(female2));
        assertDoesNotThrow(() -> new Sex(sampleGender));
    }

    @Test
    public void equalsTest() {
        Sex testSex1 = new Sex(male1);
        Sex testSex2 = new Sex(male1);
        Sex testSex3 = new Sex(female1);
        assertTrue(testSex1.equals(testSex2));
        assertFalse(testSex2.equals(testSex3));
        assertFalse(testSex1.equals(testSex3));
    }

    @Test
    public void toStringTest() {
        Sex testSex1 = new Sex(male1);
        assertTrue(testSex1.toString().equals(male1)); // Comparing "M" with "M"
        assertFalse(testSex1.toString().equals(female2)); // Comparing "F" with "M"
    }

    @Test
    public void isValidSexTest() {
        String emptyInput = "INSERT STUDENT SEX HERE!";
        assertTrue(Sex.isDefaultSex(emptyInput));
        assertFalse(Sex.isDefaultSex(male1));
        assertTrue(male1.matches(Sex.VALIDATION_REGEX));
        assertFalse(invalidGender1.matches(Sex.VALIDATION_REGEX));
    }
}