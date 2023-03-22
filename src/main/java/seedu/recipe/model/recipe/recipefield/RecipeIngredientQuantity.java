package seedu.recipe.model.recipe.recipefield;

import seedu.recipe.model.recipe.exceptions.RecipeQuantityInvalidArgumentException;
import seedu.recipe.model.recipe.unit.RecipeQuantityUnit;

import java.util.Objects;

public class RecipeIngredientQuantity {
    private final double amount;
    private final RecipeQuantityUnit unit;

    private static final String ALPHA_AMOUNT_REGEX = "[aA]|[Oo]ne";
    private static final String AMOUNT_REGEX = String.format("([1-9][0-9]*(\\.[0-9]*[1-9])?|%s)", ALPHA_AMOUNT_REGEX);
    private static final String VALIDATION_REGEX = String.format("^%s(\\s+\\S+)+", AMOUNT_REGEX);
    public static final String MESSAGE_CONSTRAINTS =
            "The quantity for a Recipe Ingredient should consist of this format:\n"
            + "`{amount} {unit}`, and the unit should comprise of alphabetic characters, "
            + "where the amount can either be a non-zero decimal, `A/a`, or `One/one`."
            + "i.e. `1 gram`, `1.5 L`, `A pinch of`, `One oz.`";

    private RecipeIngredientQuantity(double amount, RecipeQuantityUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    private static boolean isValidRecipeQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static RecipeIngredientQuantity of(String candidate) {
        if (!isValidRecipeQuantity(candidate)) {
            throw new RecipeQuantityInvalidArgumentException(candidate);
        }
        String[] tokens = candidate.split("\\s+", 2);
        double amount;
        if (tokens[0].matches(ALPHA_AMOUNT_REGEX)) {
            amount = 1;
        } else {
            amount = Double.parseDouble(tokens[0]);
        }
        RecipeQuantityUnit unit = new RecipeQuantityUnit(tokens[1]);
        return new RecipeIngredientQuantity(amount, unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit);
    }

    @Override
    public String toString() {
        return String.format("%s %s", amount, unit);
    }
}
