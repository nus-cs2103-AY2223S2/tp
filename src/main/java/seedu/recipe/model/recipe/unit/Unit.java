package seedu.recipe.model.recipe.unit;

import static java.util.Objects.requireNonNull;

public abstract class Unit {

    public final String unit;

    public Unit(String unit) {
        requireNonNull(unit);
        this.unit = unit;
    }
}
