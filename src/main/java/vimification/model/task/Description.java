package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a blank string)
     * becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*";

    public final String description;

    /**
     * Constructs a {@code Name}.
     *
     * @param description A valid name.
     */
    public Description(String description) {
        requireNonNull(description);
        // TODO : UNSUPPRESS checking
        // checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
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
}
