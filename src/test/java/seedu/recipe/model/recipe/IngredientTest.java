package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, null, null, null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("", 2.0, "kg", 40.0));
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
        assertTrue(Ingredient.isValidIngredient("tomato 1.0 unit 0.3"));
        assertTrue(Ingredient.isValidIngredient("butter 1.5 stick 0.6"));
    }
}
