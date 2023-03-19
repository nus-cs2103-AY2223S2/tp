package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Card's question in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuestion(String)}
 */
public class Question {

    public static final String MESSAGE_CONSTRAINTS = "Question should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String question;

    /**
     * Constructs a {@code Question}.
     *
     * @param question A valid name.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return question;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Question // instanceof handles nulls
                && question.equals(((Question) other).question)); // state check
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

}
