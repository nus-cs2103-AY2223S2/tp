package seedu.recipe.storage.jsonadapters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.IngredientBuilder;

/**
 * Jackson-friendly version of {@link IngredientBuilder}.
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
     * Converts a given {@code IngredientBuilder} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(IngredientBuilder source) {
        ingredientName = source.name;
    }

    @JsonValue
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code IngredientBuilder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public IngredientBuilder toModelType() throws IllegalValueException {
        try {
            return new IngredientBuilder(ingredientName);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(IngredientBuilder.MESSAGE_CONSTRAINTS);
        }
    }
}
