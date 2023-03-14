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
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidMedication));
    }

    @Test
    public void isValidMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication(null));
    }

    @Test
    public void isValidMedication_whitespaceOnly_returnsFalse() {
        assertFalse(Medication.isValidMedication(" "));
    }

    @Test
    public void isValidMedication_oneLetter_returnsFalse() {
        assertFalse(Medication.isValidMedication("a")); // 1 word
    }

    @Test
    public void isValidMedication_twoSeparateLetters_returnsFalse() {
        assertFalse(Medication.isValidMedication("a b")); // 2 words
    }

    @Test
    public void isValidMedication_emptyString_returnsTrue() {
        assertTrue(Medication.isValidMedication(""));
    }

    @Test
    public void isValidMedication_simpleCase_returnsTrue() {
        assertTrue(Medication.isValidMedication("50 drugA"));
    }

    @Test
    public void isValidMedication_medicationWithWhitespace_returnsTrue() {
        assertTrue(Medication.isValidMedication("50 drug A"));
    }

    @Test
    public void isValidMedication_twoMedicationNoSpace_returnsTrue() {
        assertTrue(Medication.isValidMedication("50 drug A;50 drug B")); // Two medications with ; no space
    }

    @Test
    public void isValidMedication_twoMedicationWithSpace_returnsTrue() {
        assertTrue(Medication.isValidMedication("50 drug A; 50 drug B")); // Two medications with ; + space
    }

    @Test
    public void isValidMedication_threeMedication_returnsTrue() {
        assertTrue(Medication.isValidMedication("20 Paracetamol 500mg; 1 DiHydrogen Monoxide; 1 physiotherapy"));
    }

}