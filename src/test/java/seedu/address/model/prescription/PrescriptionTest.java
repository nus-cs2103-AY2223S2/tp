package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PrescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Prescription(null, null));
    }

    @Test
    public void equals() {
        String validMedication = "paracetamol";
        String validCost = "3.5";

        Medication medication = new Medication(validMedication);
        Cost cost = new Cost(validCost);
        Prescription prescription = new Prescription(medication, cost);

        assertTrue(prescription.equals(prescription));
        assertTrue(prescription.equals(new Prescription(new Medication(validMedication), new Cost(validCost))));

        assertFalse(prescription.equals(new Prescription(new Medication("different med"), cost)));
        assertFalse(prescription.equals(new Prescription(medication, new Cost("1234567"))));
    }
}
