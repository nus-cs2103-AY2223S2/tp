package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents an Ingredient in a list of ingredients in a recipe.
 */
public class Ingredient {
    public static final String INGREDIENT_WRONG_ARGUMENTS_MESSAGE_CONSTRAINTS =
            "The wrong arguments have been passed into ingredients!\n"
                    + "Format: i/INGREDIENT_NAME, QUANTITY, UNIT_OF_MEASUREMENT, PRICE_PER_UNIT\n"
                    + "INGREDIENT_NAME and UNIT_OF_MEASUREMENT must be a word or sentence.\n"
                    + "QUANTITY and PRICE_PER_UNIT must be numeric.\n"
                    + "Example: i/White wine vinegar, 2, tbsp, 0.10\n";

    public static final String INGREDIENT_NAME_MESSAGE_CONSTRAINTS =
            "An ingredient should only contain alphanumeric characters and spaces, and it should not be blank.";

    public static final String INGREDIENT_QUANTITY_MESSAGE_CONSTRAINTS =
            "An ingredient should have a quantity of more than 0.";

    public static final String INGREDIENT_UOM_MESSAGE_CONSTRAINTS =
            "The unit of measurement you define should only contain alphanumeric characters "
                    + "and spaces, and it should not be blank.";

    public static final String INGREDIENT_PPU_MESSAGE_CONSTRAINTS =
            "The price per unit of an ingredient should be more than OR equals to 0.";

    public static final String INGREDIENT_FORMAT = "Format: i/INGREDIENT_NAME, QUANTITY, "
            + "UNIT_OF_MEASUREMENT, PRICE_PER_UNIT";



    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    public final Double quantity;

    public final String unitOfMeasurement;

    public final Double pricePerUnit;

    /**
     * Constructs an {@code Ingredient}.
     *
     * @param name a valid ingredient name.
     * @param quantity a valid ingredient quantity.
     * @param unitOfMeasurement a valid ingredient unit_of_measurement.
     * @param pricePerUnit a valid ingredient price_per_unit.
     */
    public Ingredient(String name, Double quantity, String unitOfMeasurement, Double pricePerUnit) {
        requireNonNull(name);
        requireNonNull(unitOfMeasurement);
        checkArgument(isValidIngredientName(name), INGREDIENT_NAME_MESSAGE_CONSTRAINTS);
        checkArgument(isValidIngredientUom(unitOfMeasurement), INGREDIENT_UOM_MESSAGE_CONSTRAINTS);
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String ingredient) {
        String trimmedIngredient = ingredient.trim();
        String[] ingredientFields = trimmedIngredient.split("\\s+");
        if (ingredientFields.length != 4) {
            return false;
        }

        Double quantity;
        Double pricePerUnit;

        if (!Ingredient.isValidIngredientName(ingredientFields[0])
                || !Ingredient.isValidIngredientUom(ingredientFields[2])) {
            return false;
        }

        try {
            quantity = Double.valueOf(ingredientFields[1]);
            pricePerUnit = Double.valueOf(ingredientFields[3]);
            if (!Ingredient.isValidIngredientQuantity(quantity)
                    || !Ingredient.isValidIngredientPpu(pricePerUnit)) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidIngredientName(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    public static boolean isValidIngredientQuantity(Double quantity) {
        return quantity > 0;
    }
    public static boolean isValidIngredientUom(String unitOfMeasurement) {
        return unitOfMeasurement.matches(VALIDATION_REGEX);
    }
    public static boolean isValidIngredientPpu(Double pricePerUnit) {
        return pricePerUnit >= 0;
    }

    /**
     * Returns the ingredient string to be displayed by the UI.
     */
    public String toDisplayString() {
        return this.name + " | " + this.quantity + " " + this.unitOfMeasurement
                + " | " + "$" + String.format("%.2f", this.pricePerUnit) + "/" + this.unitOfMeasurement;
    }
    @Override
    public String toString() {
        return this.name + "," + this.quantity
                + "," + this.unitOfMeasurement + "," + this.pricePerUnit;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && this.name.equals(((Ingredient) other).name)
                && this.quantity.equals(((Ingredient) other).quantity)
                && this.unitOfMeasurement.equals(((Ingredient) other).unitOfMeasurement)
                && this.pricePerUnit.equals(((Ingredient) other).pricePerUnit)); // state check
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
