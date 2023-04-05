package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    private String validAge1 = "0";
    private String validAge2 = "57";
    private String validAge3 = String.valueOf(Integer.MAX_VALUE);
    private String sampleAge1 = "Insert parent age here!";
    private String sampleAge2 = "Insert student age here!";
    private String invalidAge1 = ""; // Empty string
    private String invalidAge2 = " "; // Spaces only
    private String invalidAge3 = "^"; // Non-alphanumeric characters
    private String invalidAge4 = "*"; // Non-alphanumeric characters
    private String invalidAge5 = "abc"; // Alphabets
    private String invalidAge6 = "-1"; // Mixture of symbols with numbers
    private String invalidAge7 = "1a"; // Mixture of alphabets with numbers
    @Test
    public void constructorTest() {
        // invalid inputs
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge1));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge2));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge3));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge4));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge5));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge6));
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge7));

        // valid inputs
        assertDoesNotThrow(() -> new Age(sampleAge1));
        assertDoesNotThrow(() -> new Age(sampleAge2));
        assertDoesNotThrow(() -> new Age(validAge1));
        assertDoesNotThrow(() -> new Age(validAge2));
        assertDoesNotThrow(() -> new Age(validAge3));
    }

    @Test
    public void equalsTest() {
        Age testAge1 = new Age(validAge1);
        Age testAge2 = new Age(validAge1);
        Age testAge3 = new Age(validAge2);
        assertTrue(testAge1.equals(testAge2));
        assertFalse(testAge2.equals(testAge3));
        assertFalse(testAge1.equals(testAge3));
    }

    @Test
    public void toStringTest() {
        Age testAge1 = new Age(validAge1);
        assertTrue(testAge1.toString().equals(validAge1)); // Comparing "0" with "0"
        assertFalse(testAge1.toString().equals(validAge2)); // Comparing "57" with "0"
    }

    @Test
    public void isValidAgeTest() {
        String age1 = "Insert parent age here!";
        String age2 = "Insert student age here!";
        assertTrue(Age.isDefaultAge(age2));
        assertTrue(Age.isDefaultAge(age1));
        assertFalse(Age.isDefaultAge(validAge1));
        assertTrue(validAge1.matches(Age.VALIDATION_REGEX));
        assertFalse(invalidAge1.matches(Age.VALIDATION_REGEX));
    }
}
