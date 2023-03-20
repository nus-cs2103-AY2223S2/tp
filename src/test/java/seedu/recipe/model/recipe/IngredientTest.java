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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(invalidIngredient));
    }

    @Test
    public void isValidIngredient() {
        // null address
        assertThrows(NullPointerException.class, () -> Ingredient.isValidIngredient(null));

        // invalid name
        assertFalse(Ingredient.isValidIngredient("")); // empty string
        assertFalse(Ingredient.isValidIngredient(" ")); // spaces only
        assertFalse(Ingredient.isValidIngredient("^")); // only non-alphanumeric characters
        assertFalse(Ingredient.isValidIngredient("tomato*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Ingredient.isValidIngredient("tomato")); // alphabets only
        assertTrue(Ingredient.isValidIngredient("12345")); // numbers only
        assertTrue(Ingredient.isValidIngredient("tomato 2nd most ripe")); // alphanumeric characters
        assertTrue(Ingredient.isValidIngredient("Capital Tomato")); // with capital letters
        assertTrue(Ingredient.isValidIngredient("Tomatoes coated with butter")); // long names
    }
}
