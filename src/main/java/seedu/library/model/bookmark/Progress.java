package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's Progress status in the Library.
 * Guarantees: immutable; is valid as declared in {@link #isValidProgress(String)}
 */
public class Progress {


    public static final String MESSAGE_CONSTRAINTS =
            "Progress should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress A valid current progress status.
     */
    public Progress(String progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);
        value = progress;
    }

    /**
     * Returns true if a given string is a valid progress status.
     */
    public static boolean isValidProgress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Progress // instanceof handles nulls
                && value.equals(((Progress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
