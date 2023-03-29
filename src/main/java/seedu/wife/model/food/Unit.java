package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;
import static seedu.wife.commons.util.StringUtil.capitalizeString;

/**
 * Represents the unit representation of the food item in WIFE
 */
public class Unit {
    public static final String VALIDATION_REGEX = "[a-zA-Z]+";
    public static final String MESSAGE_CONSTRAINTS = "Unit should only contain letters and cannot be blank";
    private String unit;

    /**
     * Constructor to create a {@code Unit}
     *
     * @param unit Unit representation user wish to classify their food item with.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(isValid(unit), MESSAGE_CONSTRAINTS);
        this.unit = capitalizeString(unit.toLowerCase());
    }

    /**
     * Returns true if the unit representation keyed in by the user only contains letters.
     */
    public static boolean isValid(String unit) {
        return unit.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object otherUnit) {
        return otherUnit == this
                || (otherUnit instanceof Unit
                && unit.equals(((Unit) otherUnit).unit));
    }

    @Override
    public String toString() {
        return this.unit;
    }
}
