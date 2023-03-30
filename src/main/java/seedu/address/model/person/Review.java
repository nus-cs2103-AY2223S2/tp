package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's review in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidReview(String)}
 */
public class Review extends InternshipApplicationAttribute {

    public static final String MESSAGE_CONSTRAINTS = "Reviews can take any values, and it should not be blank";

    /*
     * The first character of the review must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Review}.
     *
     * @param review A valid review.
     */
    public Review(String review) {
        requireNonNull(review);
        checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        value = review;
    }

    /**
     * Returns true if a given string is a valid review.
     */
    public static boolean isValidReview(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && value.equals(((Review) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

