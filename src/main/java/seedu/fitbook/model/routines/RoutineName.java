package seedu.fitbook.model.routines;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Routine's name in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoutineName(String)}
 */
public class RoutineName {

    public static final String MESSAGE_CONSTRAINTS =
            "Routine Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String routineName;

    /**
     * Constructs a {@code RoutineName}.
     *
     * @param routineName A valid routine name.
     */
    public RoutineName(String routineName) {
        requireNonNull(routineName);
        checkArgument(isValidRoutineName(routineName), MESSAGE_CONSTRAINTS);
        this.routineName = routineName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRoutineName(String test) {
        return test.matches(VALIDATION_REGEX)
                && !test.trim().isEmpty()
                && !test.isEmpty();
    }


    @Override
    public String toString() {
        return routineName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineName // instanceof handles nulls
                && routineName.equals(((RoutineName) other).routineName)); // state check
    }

    @Override
    public int hashCode() {
        return routineName.hashCode();
    }

}
