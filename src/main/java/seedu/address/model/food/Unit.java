package seedu.address.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the unit representation of the food item in WIFE
 */
public class Unit {
    public static final String VALIDATION_REGEX = "[a-zA-Z]+";
    public static final String MESSAGE_CONSTRAINT = "Unit should only contain letters and cannot be blank";
    private String unit;

    /**
     * Constructor to create a {@code Unit}
     *
     * @param unit Unit representation user wish to classify their food item with.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(isValid(unit), MESSAGE_CONSTRAINT);
        this.unit = unit;
    }

    /**
     * Returns true if the unit representation keyed in by the user only contains letters.
     */
    public boolean isValid(String unit) {
        return unit.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.unit;
    }
}
