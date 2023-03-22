package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RecipeIngredientTest {
    private static final String VALID_INTEGER = "1 watermelon";
    private static final String VALID_DECIMAL = "13.5 g molasses extract";
    private static final String VALID_INTEGER_CONCAT_UNIT = "300g rice, washed";
    private static final String VALID_DECIMAL_CONCAT_UNIT = "10.35oz. powder (can be substituted with flour)";
    private static final String VALID_ALPHA = "butter";
    private static final String TRAILING_WHITESPACE = "watermelon juice ";
    private static final String WHITESPACE = "  ";
    private static final String LEADING_WHITESPACE = " juice of 1 carrot";
    private static final String SLASH_UNIT = "1/3 cup milk";

    @Test
    public void null_name() {
        assertThrows(NullPointerException.class, () -> new RecipeIngredient(null));
    }

    @Test
    public void constructor_invalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredient(TRAILING_WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredient(WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredient(LEADING_WHITESPACE));
    }

    @Test
    public void test_toString() {
        assertEquals(VALID_INTEGER_CONCAT_UNIT, new RecipeIngredient(VALID_INTEGER_CONCAT_UNIT).toString());
    }

    @Test
    public void constructor_invalidIngredient_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredient(invalidIngredient));
    }

    @Test
    public void ingredient_name_regex() {
        assertTrue(RecipeIngredient.isValidIngredient(VALID_INTEGER));
        assertTrue(RecipeIngredient.isValidIngredient(VALID_DECIMAL));
        assertTrue(RecipeIngredient.isValidIngredient(VALID_INTEGER_CONCAT_UNIT));
        assertTrue(RecipeIngredient.isValidIngredient(VALID_DECIMAL_CONCAT_UNIT));
        assertTrue(RecipeIngredient.isValidIngredient(VALID_ALPHA));
        assertTrue(RecipeIngredient.isValidIngredient(SLASH_UNIT));

        assertFalse(RecipeIngredient.isValidIngredient(TRAILING_WHITESPACE));
        assertFalse(RecipeIngredient.isValidIngredient(WHITESPACE));
        assertFalse(RecipeIngredient.isValidIngredient(LEADING_WHITESPACE));
    }

    @Test
    public void test_equals() {
        //Referential Equality
        RecipeIngredient test = new RecipeIngredient(VALID_DECIMAL_CONCAT_UNIT);
        assertEquals(test, test);

        //Same name
        assertEquals(new RecipeIngredient(VALID_INTEGER_CONCAT_UNIT),
                new RecipeIngredient(VALID_INTEGER_CONCAT_UNIT));

        //Diff name
        assertNotEquals(new RecipeIngredient(VALID_INTEGER_CONCAT_UNIT),
                new RecipeIngredient(VALID_DECIMAL_CONCAT_UNIT));

        //Diff type
        assertNotEquals(new RecipeIngredient(VALID_DECIMAL), "hello");

        //null
        assertNotEquals(new RecipeIngredient(VALID_DECIMAL), null);
    }

    @Test
    public void test_hashCode() {
        int expected = VALID_DECIMAL_CONCAT_UNIT.hashCode();
        assertEquals(expected, new RecipeIngredient(VALID_DECIMAL_CONCAT_UNIT).hashCode());
    }
}
