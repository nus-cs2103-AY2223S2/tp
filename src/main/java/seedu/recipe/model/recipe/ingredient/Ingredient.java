package seedu.recipe.model.recipe.ingredient;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents an ingredient that is used in a {@code Recipe}.
 */
public class Ingredient {
    public static final String MESSAGE = "An ingredient should be made up of one or more groups of "
            + "whitespace separated alphabetic characters. These characters may also be separated by "
            + "singular hyphens, such as 'self-raising flour'.";
    private static final String WORD_GROUP = "[A-Za-z]+(\\-[A-Za-z]+)?";
    public static final String VALIDATION_REGEX = String.format("^%s(\\s+%s)*$", WORD_GROUP, WORD_GROUP);
    private final String name;

    private Ingredient(String name) {
        this.name = name;
    }

    /**
     * Given a String ingredient name, parses it for validity and returns a new
     * Ingredient instance if it is valid.
     * @param s The Ingredient name to construct from.
     * @return The generated Ingredient instance.
     */
    public static Ingredient of(String s) {
        checkArgument(isValidIngredientName(s), MESSAGE);
        return new Ingredient(s);
    }

    /**
     * Given a String ingredient name, parses it for validity and returns a
     * boolean value indicating if it is valid.
     * @param s The ingredient name to test.
     * @return True if the ingredient name is valid, False otherwise.
     */
    public static boolean isValidIngredientName(String s) {
        return s.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
