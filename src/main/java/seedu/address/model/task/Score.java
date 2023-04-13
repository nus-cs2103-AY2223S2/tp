package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's score in the address book.
 */
public class Score {
    public static final String MESSAGE_CONSTRAINTS =
            "Scores should only contain numeric characters and be in the range 0-5, and it should not be blank";

    public final int score;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid score.
     */
    public Score(int score) {
        requireNonNull(score);
        //checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.score = score;
    }

    public static boolean isValidScore(int test) {
        return test >= 0 && test <= 5;
    }

    public int getValue() {
        return this.score;
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.Score // instanceof handles nulls
                && score == ((seedu.address.model.task.Score) other).score); // state check
    }

}
