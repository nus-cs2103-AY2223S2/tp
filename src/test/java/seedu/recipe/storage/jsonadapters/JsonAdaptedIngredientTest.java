package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_INGREDIENTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;

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
        CACIO_INGREDIENTS.forEach(ingredientBuilder ->
                ingredientBuilder.build() //Ingredient Map
                        .forEach((mainIngredient, ingredientInformation) -> {
                            try {
                                new JsonAdaptedIngredient(mainIngredient, ingredientInformation)
                                        .toModelType()
                                        .forEach(ingredientList::put);
                            } catch (IllegalValueException e) {
                                fail(e.getMessage());
                            }
                        }
        ));
        assertEquals(CACIO_E_PEPE.getIngredients(), ingredientList);
    }

}
