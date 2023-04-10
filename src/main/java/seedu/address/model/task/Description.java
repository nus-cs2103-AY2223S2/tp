package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a description of a Task.
 * Guarantees: Non-null description in valid form.
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces,"
                    + " and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\s:-]*$";
    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description description of a Task.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        boolean isNotFullyWhitespaces = !test.trim().isBlank();
        return test.matches(VALIDATION_REGEX) && isNotFullyWhitespaces;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
