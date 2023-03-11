package seedu.address.model.score;

import static java.util.Objects.requireNonNull;

import java.util.Objects;


/**
 * Represents a Score in the address book.
 * Guarantees: immutable; fields are validated; details are present and not null;
 */
public class Score {

    public static final String MESSAGE_CONSTRAINTS = "Score format is wrong!";

    // Identity field(s)
    public final Label scoreLabel;
    public final ScoreValue scoreValue;
    public final Date scoreDate;

    /**
     * Constructs a {@code Score}.
     *
     * @param scoreLabel A valid Score name.
     * @param scoreValue A valid Score value.
     * @param scoreDate A valid Score date.
     */
    public Score(Label scoreLabel, ScoreValue scoreValue, Date scoreDate) {
        requireNonNull(scoreLabel);
        requireNonNull(scoreValue);
        requireNonNull(scoreDate);

        this.scoreLabel = scoreLabel;
        this.scoreValue = scoreValue;
        this.scoreDate = scoreDate;
    }

    /**
     * Returns the score name.
     */
    public Label getLabel() {
        return this.scoreLabel;
    }

    /**
     * Returns the score value.
     */
    public ScoreValue getValue() {
        return this.scoreValue;
    }

    /**
     * Returns the score date in the format of yyyy-MM-dd HH:mm:ss.
     */
    public Date getDate() {
        return this.scoreDate;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && scoreLabel.equals(((Score) other).scoreLabel)
                && scoreValue == ((Score) other).scoreValue
                && scoreDate.equals(((Score) other).scoreDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreLabel, scoreValue, scoreDate);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Label: ")
                .append(getLabel())
                .append("; Score: ")
                .append(getValue())
                .append("; Date: ")
                .append(getDate());

        return builder.toString();
    }
}
