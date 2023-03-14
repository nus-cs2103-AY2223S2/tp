package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.TimeUnit;

import java.util.Objects;

public class RecipeDuration {

    private final double time;
    private final TimeUnit unit;

    public RecipeDuration(double time, TimeUnit unit){
        this.time = time;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return time + " " + unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, unit);
    }
}
