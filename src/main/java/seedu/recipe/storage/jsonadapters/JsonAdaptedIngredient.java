package seedu.recipe.storage.jsonadapters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.RecipeIngredient;

/**
 * Jackson-friendly version of {@link RecipeIngredient}.
 */
@JsonInclude(Include.NON_NULL)
public class JsonAdaptedIngredient {

    private final String ingredientName;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * Converts a given {@code RecipeIngredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(RecipeIngredient source) {
        ingredientName = source.name;
    }

    @JsonValue
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code RecipeIngredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public RecipeIngredient toModelType() throws IllegalValueException {
        try {
            return new RecipeIngredient(ingredientName);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(RecipeIngredient.MESSAGE_CONSTRAINTS);
        }
    }
}
