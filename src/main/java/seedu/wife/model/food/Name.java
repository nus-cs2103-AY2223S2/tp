package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;

/**
 * Represent a food's name stored in WIFE.
 * Name once created is immutable.
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS = "Food name should not be blank and it should only contain "
            + "alphanumeric characters and spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String foodName;

    /**
     * Construct a {@code foodName}
     * @param foodName A non-null name for the food item.
     */
    public Name(String foodName) {
        requireNonNull(foodName);
        checkArgument(isValid(foodName), MESSAGE_CONSTRAINTS);
        this.foodName = foodName;
    }

    public static boolean isValid(String foodName) {
        return foodName.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.foodName;
    }

    @Override
    public boolean equals(Object otherName) {
        return otherName == this
                || (otherName instanceof Name
                && foodName.equals(((Name) otherName).foodName));
    }

    @Override
    public int hashCode() {
        return this.foodName.hashCode();
    }
}
