package mycelium.mycelium.model.client;

import static mycelium.mycelium.commons.util.AppUtil.checkArgument;

/**
 * The YearOfBirth class represents a year of birth in the application.
 * It provides methods for validating a year of birth, and storing and retrieving a year of birth value.
 */
public class YearOfBirth {
    public static final String MESSAGE_CONSTRAINTS =
        "Year of birth should only contain numbers, and it should be 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4}";
    public final String value;

    /**
     * Constructs a YearOfBirth object with the given year of birth value.
     * Throws a NullPointerException if the input value is null.
     * Throws an IllegalArgumentException if the input value is not a valid year of birth.
     *
     * @param yearOfBirth The year of birth to be stored in the object.
     */
    public YearOfBirth(String yearOfBirth) {
        checkArgument(isValidYearOfBirth(yearOfBirth), MESSAGE_CONSTRAINTS);
        value = yearOfBirth;
    }

    /**
     * Determines whether the given string is a valid year of birth.
     *
     * @param test The string to test.
     * @return true if the string is a valid year of birth, false otherwise.
     */
    public static boolean isValidYearOfBirth(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a string representation of the year of birth value.
     *
     * @return The year of birth value as a string.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Determines whether the given object is equal to this YearOfBirth object.
     *
     * @param other The object to compare to this YearOfBirth object.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof YearOfBirth // instanceof handles nulls
            && value.equals(((YearOfBirth) other).value)); // state check
    }

    /**
     * Returns the hash code value for this YearOfBirth object.
     *
     * @return The hash code value for this YearOfBirth object.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
