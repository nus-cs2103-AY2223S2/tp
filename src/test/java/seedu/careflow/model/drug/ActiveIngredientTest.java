package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class ActiveIngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ActiveIngredient(null));
    }

    @Test
    public void constructor_invalidActiveIngredient_throwsIllegalArgumentException() {
        String invalidActiveIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new ActiveIngredient(invalidActiveIngredient));
    }

    @Test
    public void isValidActiveIngredient() {
        // null
        assertThrows(NullPointerException.class, () -> ActiveIngredient.isValidIngredient(null));

        // invalid active ingredient
        assertFalse(ActiveIngredient.isValidIngredient("")); // empty string
        assertFalse(ActiveIngredient.isValidIngredient(" ")); // spaces only
        // with special character that aren't allowed
        assertFalse(ActiveIngredient.isValidIngredient("amoxicillin*"));
        // long description exceed 200 char limit
        assertFalse(ActiveIngredient.isValidIngredient(StringUtil.generateRandomString(201)));

        // valid active ingredient
        assertTrue(ActiveIngredient.isValidIngredient("amoxicillin"));
        assertTrue(ActiveIngredient.isValidIngredient("a")); // one character
        assertTrue(ActiveIngredient.isValidIngredient("long long long long long description"));
        // long description with exactly 200 char
        assertTrue(ActiveIngredient.isValidIngredient("a" + StringUtil.generateRandomString(199)));
    }

    @Test
    public void equals() {
        ActiveIngredient activeIngredient = new ActiveIngredient("sertraline hydrochloride");
        // null -> returns false
        assertFalse(activeIngredient.equals(null));

        // same object -> returns true
        assertTrue(activeIngredient.equals(activeIngredient));

        // same values -> returns true
        assertTrue(activeIngredient.equals(new ActiveIngredient("sertraline hydrochloride")));

        // different values -> return false
        assertFalse(activeIngredient.equals("omeprazole magnesium"));
    }
}
