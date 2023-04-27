package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;
import static seedu.wife.commons.util.StringUtil.capitalizeString;

/**
 * Represents the unit representation of the food item in WIFE
 */
public class Unit {
    public static final Integer UNIT_NAME_MAX_LENGTH = 10;
    public static final String VALIDATION_REGEX = "[a-zA-Z]+";
    public static final String MESSAGE_CONSTRAINTS = "Unit should only contain letters and cannot be blank";
    public static final String UNIT_NAME_LENGTHY = "Unit should have maximum 10 characters.";
    public static final String UNIT_NOT_PRESENT = "Unit should not be empty. "
            + "Please insert a unit for your food.";
    private String unit;

    /**
     * Constructor to create a {@code Unit}
     *
     * @param unit Unit representation user wish to classify their food item with.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(isUnitPresent(unit), UNIT_NOT_PRESENT);
        checkArgument(isUnitNotLengthy(unit), UNIT_NAME_LENGTHY);
        checkArgument(isValidUnit(unit), MESSAGE_CONSTRAINTS);
        this.unit = capitalizeString(unit.toLowerCase());
    }

    /**
     * Returns true if the unit representation keyed in by the user only contains letters.
     */
    public static boolean isValidUnit(String unit) {
        return unit.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given name is not blank.
     */
    public static Boolean isUnitPresent(String unit) {
        return !unit.isBlank();
    }

    /**
     * Returns true if the given name has length not more than 10.
     */
    public static Boolean isUnitNotLengthy(String unit) {
        return unit.length() <= UNIT_NAME_MAX_LENGTH;
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
