package seedu.recipe.model.recipe;

import java.util.function.Predicate;

/**
 * Tests that a {@code Recipe} is starred or not.
 */
public class IsStarredPredicate implements Predicate<Recipe> {
    public IsStarredPredicate() {
    }

    @Override
    public boolean test(Recipe recipe) {
        return recipe.isStarred();
    }
}
