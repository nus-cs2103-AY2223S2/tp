package seedu.address.model.video;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a video's name in the tracker.
 * Guarantees: immutable, is valid as declared in {@link #isValidName(String)}.
 */
public class VideoName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code VideoName}.
     *
     * @param name A valid name.
     */
    public VideoName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if {@code test} is a valid name.
     *
     * @param test The string to check if it is a valid name.
     * @return True if {@code test} is a valid name. Otherwise, false.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VideoName // instanceof handles nulls
                && name.equals(((VideoName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
