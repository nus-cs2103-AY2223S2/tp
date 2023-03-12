package seedu.careflow.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

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
    public void isValidDrugAllergy() {
        // null
        assertThrows(NullPointerException.class, () -> DrugAllergy.isValidDrugAllergy(null));

        // blank drug allergy description
        assertFalse(DrugAllergy.isValidDrugAllergy("")); // empty string
        assertFalse(DrugAllergy.isValidDrugAllergy(" ")); // spaces only

        // invalid drug allergy description
        assertFalse(DrugAllergy.isValidDrugAllergy("With empty blank"));
        assertFalse(DrugAllergy.isValidDrugAllergy("WithSpecialCharacter["));

        // valid drug allergy description
        assertTrue(DrugAllergy.isValidDrugAllergy("Penicillin"));
        assertTrue(DrugAllergy.isValidDrugAllergy("peanuts"));
    }
}
