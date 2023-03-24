package seedu.recipe.model.recipe.ingredient;

import java.util.Arrays;
import java.util.Objects;

import seedu.recipe.model.recipe.exceptions.RecipeQuantityInvalidArgumentException;
import seedu.recipe.model.recipe.unit.RecipeIngredientQuantityUnit;

/**
 * Represents the Quantity field associated with a {@code IngredientBuilder}.
 */
public class IngredientQuantity {
    public static final String MESSAGE_CONSTRAINTS =
            "The quantity for a Recipe IngredientBuilder should consist of this format:\n"
            + "`{amount} {unit}`, and the unit should comprise of alphabetic characters, "
            + "where the amount can either be a non-zero decimal, `A/a`, or `One/one`."
            + "i.e. `1 gram`, `1.5 L`, `A pinch of`, `One oz.`";

    private static final String ALPHA_AMOUNT_REGEX = "[aA]|[Oo]ne";
    private static final String RANGE_REGEX = "\\d+\\s*(\\-|to)\\s*\\d+";
    private static final String FRACTION_REGEX = "\\d+/\\d+";
    private static final String AMOUNT_REGEX = String.format(
            "([0-9]+([\\./][0-9]*[1-9])?|%s)", ALPHA_AMOUNT_REGEX);
    private static final String VALIDATION_REGEX = String.format("^%s(\\s+\\S+)*", AMOUNT_REGEX);

    private final String amount;

    private IngredientQuantity(String amount) {
        this.amount = amount;
    }

    private static boolean isValidRecipeQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
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
        String[] tokens = candidate.split("\\s+", 2);
        double amount = 0;
        double upperLimit = -1;
        if (tokens[0].matches(ALPHA_AMOUNT_REGEX)) {
            amount = 1;
        } else if (tokens[0].matches(FRACTION_REGEX)) {
            String[] fractionComponents = tokens[0].split("/");
            double numerator = Integer.parseInt(fractionComponents[0]);
            double denominator = Integer.parseInt(fractionComponents[1]);
            if (denominator == 0) {
                throw new RecipeQuantityInvalidArgumentException(candidate);
            }
            amount = numerator;
            upperLimit = denominator;

        } else if (tokens[0].matches(RANGE_REGEX)) {
            String[] rangeComponents = tokens[0].split("-|to");
            double lowerRange = Integer.parseInt(rangeComponents[0]);
            double upperRange = Integer.parseInt(rangeComponents[1]);
            amount = lowerRange;
            upperLimit = upperRange;
            if (lowerRange > upperRange) {
                throw new RecipeQuantityInvalidArgumentException(candidate);
            }
        } else {
            amount = Double.parseDouble(tokens[0]);
        }
        if (amount == 0) {
            throw new RecipeQuantityInvalidArgumentException(candidate);
        }
        RecipeIngredientQuantityUnit unit = null;
        if (tokens.length > 1) {
            unit = new RecipeIngredientQuantityUnit(tokens[1]);
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
