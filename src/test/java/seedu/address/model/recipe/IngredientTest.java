package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Ingredient.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Ingredient.isValidPhone("")); // empty string
        assertFalse(Ingredient.isValidPhone(" ")); // spaces only
        assertFalse(Ingredient.isValidPhone("91")); // less than 3 numbers
        assertFalse(Ingredient.isValidPhone("phone")); // non-numeric
        assertFalse(Ingredient.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Ingredient.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Ingredient.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Ingredient.isValidPhone("93121534"));
        assertTrue(Ingredient.isValidPhone("124293842033123")); // long phone numbers
    }
}
