package seedu.recipe.commons.util;

import seedu.recipe.model.recipe.Recipe;

/**
 * Comparison metric for SortCommand.
 */
public class RecipeComparatorUtil implements java.util.Comparator<Recipe> {
    @Override
    public int compare(Recipe a, Recipe b) {
        Double diff = a.getCost() - b.getCost();
        if (diff > 0) {
            return 1;
        } else if (diff == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
