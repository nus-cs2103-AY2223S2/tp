package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null));
    }

    @Test
    public void constructor_invalidMedication_throwsIllegalArgumentException() {
        String invalidMedication = " ";
        assertThrows(IllegalArgumentException.class, () -> new Medication(invalidMedication));
    }

    @Test
    public void isValidMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication(null));
    }

    @Test
    public void isValidMedication() {
        assertFalse(Medication.isValidMedication(" "));
        assertFalse(Medication.isValidMedication(""));

        assertTrue(Medication.isValidMedication("Paracetamol"));
        assertTrue(Medication.isValidMedication("Drug A"));
    }
}
