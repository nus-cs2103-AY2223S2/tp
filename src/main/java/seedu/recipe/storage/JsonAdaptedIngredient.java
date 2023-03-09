package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Ingredient;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
class JsonAdaptedIngredient {

    private final String ingredient;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        ingredient = source.ingredient;
    }

    @JsonValue
    public String getIngredient() {
        return ingredient;
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        if (!Ingredient.isValidIngredient(ingredient)) {
            throw new IllegalValueException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        return new Ingredient(ingredient);
    }

}
