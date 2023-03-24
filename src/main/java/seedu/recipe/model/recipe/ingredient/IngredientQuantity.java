package seedu.recipe.model.recipe.ingredient;

import seedu.recipe.model.recipe.exceptions.RecipeQuantityInvalidArgumentException;

/**
 * Represents the Quantity field associated with a {@code IngredientBuilder}.
 */
public class IngredientQuantity {
    public static final String MESSAGE_CONSTRAINTS =
            "The quantity field for a Recipe Ingredient should consist of this format: "
            + "`{amount} {unit}`, and the unit should comprise of alphabetic characters, "
            + "where the amount can either be a non-zero decimal, `A/a`, or `One/one`."
            + "i.e. `1 gram`, `1.5 L`, `A pinch of`, `One oz.`";

    private static final String ALPHA_AMOUNT_REGEX = "[aA]|[Oo]ne";
    private static final String RANGE_REGEX = "\\d+\\s*(\\-|to)\\s*\\d+";
    private static final String AMOUNT_REGEX = String.format(
            "([1-9][0-9]*|[0-9]+[\\./][0-9]*[1-9]|%s|%s)", ALPHA_AMOUNT_REGEX, RANGE_REGEX);
    private static final String VALIDATION_REGEX = String.format("^%s(\\s+[A-Za-z]+\\.?)*", AMOUNT_REGEX);

    private final String amount;

    private IngredientQuantity(String amount) {
        this.amount = amount;
    }

    private static boolean isValidRecipeQuantity(String candidate) {
        if (!candidate.matches(VALIDATION_REGEX)) {
            return false;
        }
        //Validate amount strings, only if it is a range.
        String[] tokens = candidate.split("\\s+", 2);
        if (tokens[0].matches(RANGE_REGEX)) {
            String[] rangeComponents = tokens[0].split("-|to");
            double lowerRange = Integer.parseInt(rangeComponents[0]);
            double upperRange = Integer.parseInt(rangeComponents[1]);
            return lowerRange < upperRange;
        }
        //Else, using regex, it is of 1 of these three formats:
        //1) "A quart of", "One gallon" - Alphabetic amount quantifiers, for singular amounts
        //2) "1 liter" - Non-zero integer amounts
        //3) "1/3 cup" - One fraction amounts -> By regex, this will be a valid fraction
        //4) "1.5L" - One decimal amount -> By regex, this will be a valid decimal
        return true;
    }

    /**
     * Validates the String parameter candidate passed in as an argument,
     * and generates and returns a IngredientQuantity instance, if
     * it is valid.
     * @param candidate The string parameter to be checked.
     * @return The quantity instance.
     */
    public static IngredientQuantity of(String candidate) {
        if (!isValidRecipeQuantity(candidate)) {
            throw new RecipeQuantityInvalidArgumentException(candidate);
        }
        return new IngredientQuantity(candidate);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return amount;
    }
}
