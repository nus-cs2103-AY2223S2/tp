package seedu.recipe.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
class JsonAdaptedIngredient {

    public final String name;

    public final Double quantity;

    public final String unitOfMeasurement;

    public final Double pricePerUnit;


    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("name") String name,
                                 @JsonProperty("quantity") Double quantity,
                                 @JsonProperty("unit_of_measurement") String unitOfMeasurement,
                                 @JsonProperty("price_per_unit") Double pricePerUnit) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        name = source.name;
        quantity = source.quantity;
        unitOfMeasurement = source.unitOfMeasurement;
        pricePerUnit = source.pricePerUnit;
    }


    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        if (!Ingredient.isValidIngredientName(name)) {
            throw new IllegalValueException(Ingredient.INGREDIENT_NAME_MESSAGE_CONSTRAINTS);
        }

        if (!Ingredient.isValidIngredientQuantity(quantity)) {
            throw new ParseException(Ingredient.INGREDIENT_QUANTITY_MESSAGE_CONSTRAINTS);
        }

        if (!Ingredient.isValidIngredientUOM(unitOfMeasurement)) {
            throw new ParseException(Ingredient.INGREDIENT_UOM_MESSAGE_CONSTRAINTS);
        }

        if (!Ingredient.isValidIngredientPPU(pricePerUnit)) {
            throw new ParseException(Ingredient.INGREDIENT_PPU_MESSAGE_CONSTRAINTS);
        }
        return new Ingredient(name, quantity, unitOfMeasurement, pricePerUnit);
    }
}
