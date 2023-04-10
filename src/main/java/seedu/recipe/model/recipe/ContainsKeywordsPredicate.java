package seedu.recipe.model.recipe;

import java.util.function.Predicate;

/**
 * Tests that a {@code Recipe}'s {@code Title} matches any of the keywords given.
 */
public abstract class ContainsKeywordsPredicate implements Predicate<Recipe> {

    public ContainsKeywordsPredicate() {
    }

    public abstract boolean test(Recipe recipe);

}
