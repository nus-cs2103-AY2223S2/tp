package seedu.address.model.score;

import static java.util.Objects.requireNonNull;

import seedu.address.model.score.exceptions.BadScoreValueException;

/**
 * Represents a Score's value in the Score object.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)}
 * {@link #isNegative(String)} {@link #isLargerThanMax(String)}
 */
public class ScoreValue {
    // Identity field(s)
    private static final Double MAX_SCORE = 100.0;
    private static final Double MIN_SCORE = 0.0;
    public final Double value;

    /**
     * Constructs a {@code ScoreValue}.
     *
     * @param value A valid Score value.
     * @exception BadScoreValueException if the value is not a valid score value.
     */
    public ScoreValue(String value) throws BadScoreValueException {
        requireNonNull(value);

        if (!isValidScore(value) || isNegative(value) || isLargerThanMax(value)) {
            throw new BadScoreValueException();
        }

        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if the value is a valid score value (0 <= s <= 100).
     */
    private boolean isValidScore(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if the value is negative.
     */
    private boolean isNegative(String value) {
        return Double.parseDouble(value) < ScoreValue.MIN_SCORE;
    }

    /**
     * Returns true if the value is larger than the maximum score.
     */
    private boolean isLargerThanMax(String value) {
        return Double.parseDouble(value) > ScoreValue.MAX_SCORE;
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
