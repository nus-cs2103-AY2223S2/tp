package seedu.powercards.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.util.AppUtil.checkArgument;

/**
 * Represents a Card's answer in the deck.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnswer(String)}
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS = "Answers can take any values, and it should not be blank";

    /*
     * The first character of the answer must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String answer;

    /**
     * Constructs an {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        this.answer = answer;
    }

    /**
     * Returns true if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return answer;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Answer // instanceof handles nulls
                && answer.equals(((Answer) other).answer)); // state check
    }

    @Override
    public int hashCode() {
        return answer.hashCode();
    }

}
