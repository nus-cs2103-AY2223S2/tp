package seedu.recipe.model.recipe.unit;

/**
 * Represents the unit of measurement for the time taken to use a certain Recipe in the RecipeBook. This can refer to
 * seconds or hours etc.
 */
public class TimeUnit extends Unit {
    public TimeUnit(String unit) {
        super(unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeUnit // instanceof handles nulls
                && unit.equals(((TimeUnit) other).unit)); // state check
    }
}
