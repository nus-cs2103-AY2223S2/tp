package seedu.recipe.storage.jsonadapters;

import org.junit.jupiter.api.Test;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_INGREDIENTS;

public class JsonAdaptedIngredientTest {

    @Test
    public void toModelType_invalidIngredientName_throwsIllegalArgumentException() {
        JsonAdaptedIngredient jsonAdaptedIngredient = new JsonAdaptedIngredient(
                "",
                "poultry",
                "500g",
                "about half a pound",
                Arrays.asList("skinless", "boneless"),
                new ArrayList<JsonAdaptedSubstitutionIngredient>()
        );
        assertThrows(IllegalArgumentException.class, jsonAdaptedIngredient::toModelType);
    }

    @Test
    public void toModelType_validIngredientDetails_returnsIngredientKeyValuePair() {
        HashMap<Ingredient, IngredientInformation> ingredientList = new HashMap<>();
        for (IngredientBuilder ingredientBuilder : CACIO_INGREDIENTS) {
            HashMap<Ingredient, IngredientInformation> ingredientMap = ingredientBuilder.build();
            ingredientMap.forEach(((ingredient, ingredientInformation) -> {
                JsonAdaptedIngredient jsonAdaptedIngredient =
                        new JsonAdaptedIngredient(ingredient, ingredientInformation);
                HashMap<Ingredient, IngredientInformation> i = null;
                try {
                    i = jsonAdaptedIngredient.toModelType();
                } catch (IllegalValueException e) {
                    throw new RuntimeException(e.getMessage());
                }
                ingredientList.putAll(i);
            }));
        }
        assertEquals(CACIO_E_PEPE.getIngredients(), ingredientList);
    }

}
