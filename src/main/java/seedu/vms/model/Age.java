package seedu.vms.model;

import seedu.vms.commons.util.AppUtil;


/**
 * Represents an age. Ensures that its value is between {@link #MIN_VALUE} and
 * {@link #MAX_VALUE} inclusive.
 */
public class Age implements Comparable<Age> {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 200;

    public static final Age MIN_AGE = new Age(MIN_VALUE);
    public static final Age MAX_AGE = new Age(MAX_VALUE);

    public static final String STRING_MIN = "MIN";
    public static final String STRING_MAX = "MAX";

    public static final String MESSAGE_CONSTRAINTS = "Age must be a positive integer";

    private final int value;


    /**
     * Constructs an {@code Age}.
     *
     * @param value - the value of the age.
     * @throws IllegalArgumentException if {@code value < 0}.
     */
    public Age(int value) {
        AppUtil.checkArgument(isValid(value), MESSAGE_CONSTRAINTS);
        this.value = getAgeValue(value);
    }


    public static boolean isValid(int value) {
        return value >= 0;
    }


    private static int getAgeValue(int value) {
        if (value >= MAX_VALUE) {
            return MAX_VALUE;
        }
        return value;
    }


    public int getValue() {
        return value;
    }


    @Override
    public int compareTo(Age other) {
        return value - other.value;
    }


    @Override
    public String toString() {
        if (value == MIN_VALUE) {
            return STRING_MIN;
        } else if (value == MAX_VALUE) {
            return STRING_MAX;
        }
        return String.valueOf(value);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof Age
                && value == ((Age) other).value;
    }


    @Override
    public int hashCode() {
        return value;
    }
}
