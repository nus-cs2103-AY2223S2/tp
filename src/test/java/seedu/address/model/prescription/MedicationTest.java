package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.prescription.Medication;

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
        assertFalse(Medication.isValidMedication("a")); // 1 word
        assertFalse(Medication.isValidMedication("a b")); // 2 words
        assertTrue(Medication.isValidMedication(""));
        assertTrue(Medication.isValidMedication("50 drugA"));
        assertTrue(Medication.isValidMedication("50 drug A"));
        assertTrue(Medication.isValidMedication("50 drug A;50 drug B")); // Two medications with ; no space
        assertTrue(Medication.isValidMedication("50 drug A; 50 drug B")); // Two medications with ; + space
        assertTrue(Medication.isValidMedication("20 Paracetamol 500mg; 1 DiHydrogen Monoxide; 1 physiotherapy"));
    }
}
