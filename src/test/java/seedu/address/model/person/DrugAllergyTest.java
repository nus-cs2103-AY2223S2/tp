package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DrugAllergyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DrugAllergy(null));
    }

    @Test
    public void constructor_invalidDrugAllergy_throwsIllegalArgumentException() {
        String invalidDrugAllergy = "";
        assertThrows(IllegalArgumentException.class, () -> new DrugAllergy(invalidDrugAllergy));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> DrugAllergy.isValidDrugAllergy(null));

        // invalid addresses
        assertFalse(DrugAllergy.isValidDrugAllergy("")); // empty string
        assertFalse(DrugAllergy.isValidDrugAllergy(" ")); // spaces only

        // valid addresses
        assertTrue(DrugAllergy.isValidDrugAllergy("Panadol"));
        assertTrue(DrugAllergy.isValidDrugAllergy("X")); // one character trade name for the drug, Xenazine
    }
}
