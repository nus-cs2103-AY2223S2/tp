package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents the rating of a book in the library.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating implements Comparable<Rating> {

    public static final Rating DEFAULT_RATING = new Rating("0");

    public static final String MESSAGE_CONSTRAINTS =
            "Rating should be integer value from 0 to 5(inclusive)";
    public static final String VALIDATION_REGEX = "[0-5]";

    public final String value;

    /**
     * Constructs a {@code Rating}.
     * @param rating of a book.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    @Override
    public String toString() {
        return value;
    }
    /**
     * Returns true if a given string is a valid url.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int compareTo(Rating other) {
        return this.value.compareTo(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
