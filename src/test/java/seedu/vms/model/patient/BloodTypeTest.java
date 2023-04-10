package seedu.vms.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidBloodType_throwsIllegalArgumentException() {
        String invalidBloodType = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType));
    }

    @Test
    public void isValidBloodType() {
        // invalid blood types
        assertFalse(BloodType.isValidBloodType("")); // empty string
        assertFalse(BloodType.isValidBloodType(" ")); // spaces only
        assertFalse(BloodType.isValidBloodType("B")); // Random
        assertFalse(BloodType.isValidBloodType("C")); // Random
        assertFalse(BloodType.isValidBloodType("A")); // Random
        assertFalse(BloodType.isValidBloodType("A++")); // Random

        // valid blood types
        assertTrue(BloodType.isValidBloodType("A-"));
        assertTrue(BloodType.isValidBloodType("A+"));
        assertTrue(BloodType.isValidBloodType("B-"));
        assertTrue(BloodType.isValidBloodType("B+"));
        assertTrue(BloodType.isValidBloodType("AB-"));
        assertTrue(BloodType.isValidBloodType("AB+"));
        assertTrue(BloodType.isValidBloodType("O-"));
        assertTrue(BloodType.isValidBloodType("O+"));
    }
}
