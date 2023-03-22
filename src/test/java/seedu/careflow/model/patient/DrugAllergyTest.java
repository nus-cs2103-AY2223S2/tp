package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

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
        assertFalse(DrugAllergy.isValidDrugAllergy("WithSpecialCharacter[")); // with special character
        // with more than 500 character including space
        assertFalse(DrugAllergy.isValidDrugAllergy(StringUtil.generateRandomString(501)));
        // with more than 500 alphanumeric character including space
        assertFalse(DrugAllergy.isValidDrugAllergy(StringUtil.generateRandomAlphaNumericString(501)));

        // valid drug allergy description
        assertTrue(DrugAllergy.isValidDrugAllergy("a")); // with one char
        assertTrue(DrugAllergy.isValidDrugAllergy("Penicillin")); // without space
        assertTrue(DrugAllergy.isValidDrugAllergy("Chemotherapy drugs")); // with space non-steroidal
        assertTrue(DrugAllergy.isValidDrugAllergy("non-steroidal")); // with special character
        assertTrue(DrugAllergy.isValidDrugAllergy(StringUtil.generateRandomString(500))); // with exactly 500 char
        // with exactly 500 alphanumeric char
        assertTrue(DrugAllergy.isValidDrugAllergy("a" + StringUtil.generateRandomAlphaNumericString(499)));
    }

    @Test
    public void equals() {
        DrugAllergy drugAllergy = new DrugAllergy("Aspirin");
        // null -> returns false
        assertFalse(drugAllergy.equals(null));

        // same object -> returns true
        assertTrue(drugAllergy.equals(drugAllergy));

        // same values -> returns true
        assertTrue(drugAllergy.equals(new DrugAllergy("Aspirin")));

        // different values -> return false
        assertFalse(drugAllergy.equals("Anticonvulsants"));
    }
}
