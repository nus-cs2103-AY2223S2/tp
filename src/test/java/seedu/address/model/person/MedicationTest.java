package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class MedicationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null));
    }

    @Test
    public void constructor_invalidMedication_throwsIllegalArgumentException() {
        String invalidMedication = "";
        assertThrows(IllegalArgumentException.class, () -> new Medication(invalidMedication));
    }

    @Test
    public void isValidMedication() {
        // null address
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication(null));

        // invalid medication
        assertFalse(Medication.isValidMedication("")); // empty string
        assertFalse(Medication.isValidMedication(" ")); // spaces only

        // valid medication
        assertTrue(Medication.isValidMedication("Histamine")); // One Word Medication
        assertTrue(Medication.isValidMedication("Amlodipine Besilate")); // Multiple Worded Medication
        assertTrue(Medication.isValidMedication("Paracetamol 500mg; DiHydrogen Monoxode")); // Long medication
    }
}
