package seedu.address.model.powercard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Powercard's Answer in the deck.
 */
public class AnswerOld {

    public static final String MESSAGE_CONSTRAINTS =
            "Answers should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String answer;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public AnswerOld(String answer) {
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnswerOld // instanceof handles nulls
                && this.answer.equals(((AnswerOld) other).answer)); // state check
    }

    @Override
    public String toString() {
        return this.answer;
    }

}
