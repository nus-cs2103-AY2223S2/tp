package seedu.address.model.score;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Score's value in the Score object.
 * Guarantees: immutable; is valid as declared in {@link #isValidScoreValue(String)}
 * {@link #isNegative(String)} {@link #isLargerThanMax(String)}
 */
public class ScoreValue {
    public static final String MESSAGE_CONSTRAINTS =
        "Score value should be a number between 0 and 100 "
        + "(with 1 decimal place [optional]), and it should not be blank";

    public static final String VALIDATION_REGEX = "^(([1-9][0-9]?|0)(\\.\\d)?|100(\\.0)?)$";

    // Identity field(s)
    public final Double value;

    /**
     * Constructs a {@code ScoreValue}.
     *
     * @param value A valid Score value.
     */
    public ScoreValue(String value) {
        requireNonNull(value);
        checkArgument(isValidScoreValue(value), MESSAGE_CONSTRAINTS);

        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if the value is a valid score value (0 <= s <= 100).
     */
    public static boolean isValidScoreValue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScoreValue // instanceof handles nulls
                && value.equals(((ScoreValue) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
