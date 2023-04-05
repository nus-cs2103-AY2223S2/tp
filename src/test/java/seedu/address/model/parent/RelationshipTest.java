package seedu.address.model.parent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.parent.Relationship;

public class RelationshipTest {
    @Test
    public void constructorTest() {
        String validRls1 = "mother";
        String validRls2 = "123";
        String validRls3 = "Guardian 1";
        String sampleRls = "Insert parent relationship to student here!";
        String invalidRls1 = ""; // Empty string
        String invalidRls2 = " "; // Spaces only
        String invalidRls3 = "^"; // Non-alphanumeric characters
        String invalidRls4 = "*"; // Non-alphanumeric characters

        // invalid inputs
        assertThrows(IllegalArgumentException.class, () -> new Relationship(invalidRls1));
        assertThrows(IllegalArgumentException.class, () -> new Relationship(invalidRls2));
        assertThrows(IllegalArgumentException.class, () -> new Relationship(invalidRls3));
        assertThrows(IllegalArgumentException.class, () -> new Relationship(invalidRls4));

        // valid inputs
        assertDoesNotThrow(() -> new Relationship(validRls1));
        assertDoesNotThrow(() -> new Relationship(validRls2));
        assertDoesNotThrow(() -> new Relationship(validRls3));
        assertDoesNotThrow(() -> new Relationship(sampleRls));
    }

    @Test
    public void equalsTest() {
        Relationship testRls1 = new Relationship("hello");
        Relationship testRls2 = new Relationship("hello");
        Relationship testRls3 = new Relationship("HELLO");
        assertTrue(testRls1.equals(testRls2));
        assertFalse(testRls1.equals(testRls3));
        assertFalse(testRls2.equals(testRls3));
    }

    @Test
    public void toStringTest() {
        String rls1 = "mother";
        String rls2 = "father";
        Relationship testRls1 = new Relationship(rls1);
        assertTrue(testRls1.toString().equals(rls1)); // Comparing "mother" with "mother"
        assertFalse(testRls1.toString().equals(rls2)); // Comparing "father" with "mother"
    }
}
