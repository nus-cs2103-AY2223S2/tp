package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Ingredient.isValidIngredient(null));

        // invalid phone numbers
        assertFalse(Ingredient.isValidIngredient("")); // empty string
        assertFalse(Ingredient.isValidIngredient(" ")); // spaces only
        assertFalse(Ingredient.isValidIngredient("91")); // less than 3 numbers
        assertFalse(Ingredient.isValidIngredient("phone")); // non-numeric
        assertFalse(Ingredient.isValidIngredient("9011p041")); // alphabets within digits
        assertFalse(Ingredient.isValidIngredient("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Ingredient.isValidIngredient("911")); // exactly 3 numbers
        assertTrue(Ingredient.isValidIngredient("93121534"));
        assertTrue(Ingredient.isValidIngredient("124293842033123")); // long phone numbers
    }
}
