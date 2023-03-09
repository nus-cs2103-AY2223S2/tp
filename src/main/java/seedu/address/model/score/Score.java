package seedu.address.model.score;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Score in the address book.
 * Guarantees: immutable; fields are validated; details are present and not null;
 */
public class Score {
    // Identity field(s)
    public final Label scoreName;
    public final ScoreValue scoreValue;
    public final Date scoreDate;

    /**
     * Constructs a {@code Score}.
     *
     * @param scoreName A valid Score name.
     * @param scoreValue A valid Score value.
     * @param scoreDate A valid Score date.
     */
    public Score(Label scoreName, ScoreValue scoreValue, Date scoreDate) {
        requireAllNonNull(scoreName, scoreValue, scoreDate);

        this.scoreName = scoreName;
        this.scoreValue = scoreValue;
        this.scoreDate = scoreDate;
    }

    /**
     * Returns the score name.
     */
    public Label getScoreName() {
        return scoreName;
    }

    /**
     * Returns the score value.
     */
    public ScoreValue getScoreValue() {
        return scoreValue;
    }

    /**
     * Returns the score date in the format of yyyy-MM-dd HH:mm:ss.
     */
    public Date getScoreDate() {
        return scoreDate;
    }

    /**
     * Returns true if both scores have the same name.
     * This defines a weaker notion of equality between two scores.
     */
    public boolean isSameScore(Score otherScore) {
        if (otherScore == this) {
            return true;
        }

        return otherScore != null
                && otherScore.getScoreName().equals(getScoreName())
                && otherScore.getScoreValue() == getScoreValue()
                && otherScore.getScoreDate().equals(getScoreDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && scoreName.equals(((Score) other).scoreName)
                && scoreValue == ((Score) other).scoreValue
                && scoreDate.equals(((Score) other).scoreDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreName, scoreValue, scoreDate);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getScoreName())
                .append("; Score: ")
                .append(getScoreValue())
                .append("; Date: ")
                .append(getScoreDate());

        return builder.toString();
    }
}
