package seedu.recipe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.storage.JsonAdaptedRecipe.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Title;

public class JsonAdaptedRecipeTest {
    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_DESC = "+6@*1234";
    private static final String INVALID_INGREDIENT = "+6@*1234";
    private static final String INVALID_STEP = "+6@*1234";
    private static final String INVALID_TAG = "";


    private static final String VALID_TITLE = CORNDOGS.getTitle().toString();
    private static final String VALID_DESC = CORNDOGS.getDesc().toString();
    private static final Set<JsonAdaptedIngredient> VALID_INGREDIENTS = CORNDOGS.getIngredients().stream()
            .map(JsonAdaptedIngredient::new)
            .collect(Collectors.toSet());
    private static final List<JsonAdaptedStep> VALID_STEPS = CORNDOGS.getSteps().stream()
            .map(JsonAdaptedStep::new)
            .collect(Collectors.toList());
    private static final Set<JsonAdaptedTag> VALID_TAG = CORNDOGS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toSet());

    @Test
    public void toModelType_validRecipeDetails_returnsRecipe() throws Exception {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(CORNDOGS);
        assertEquals(CORNDOGS, recipe.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(INVALID_TITLE, VALID_DESC, VALID_INGREDIENTS, VALID_STEPS, VALID_TAG, false);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(null, VALID_DESC, VALID_INGREDIENTS, VALID_STEPS,
                VALID_TAG, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_TITLE, INVALID_DESC, VALID_INGREDIENTS,
                VALID_STEPS, VALID_TAG, false);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullDesc_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_TITLE, null, VALID_INGREDIENTS, VALID_STEPS,
                VALID_TAG, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredients_throwsIllegalValueException() {
        Set<JsonAdaptedIngredient> invalidIngredients = new HashSet<>(VALID_INGREDIENTS);
        invalidIngredients.add(new JsonAdaptedIngredient(INVALID_INGREDIENT));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_TITLE, VALID_DESC, invalidIngredients,
                VALID_STEPS, VALID_TAG, false);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidSteps_throwsIllegalValueException() {
        List<JsonAdaptedStep> invalidSteps = new ArrayList<>(VALID_STEPS);
        invalidSteps.add(new JsonAdaptedStep(INVALID_STEP));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_TITLE, VALID_DESC, VALID_INGREDIENTS,
                invalidSteps, VALID_TAG, false);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        Set<JsonAdaptedTag> invalidTags = new HashSet<>(VALID_TAG);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_TITLE, VALID_DESC, VALID_INGREDIENTS,
                VALID_STEPS, invalidTags, false);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }
}
