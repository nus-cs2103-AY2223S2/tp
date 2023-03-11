package seedu.address.model.score;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the score title in a score.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Label {

    public static final String MESSAGE_CONSTRAINTS =
            "Label should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String label;

    /**
     * Constructs a {@code Title}.
     *
     * @param label A valid title.
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        this.label = label;
    }

    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Label // instanceof handles nulls
                && label.equals(((Label) other).label)); // state check
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
