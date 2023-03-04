package seedu.address.model.client.routines;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Routine in the FitBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRoutineName(String)}
 */
public class Routine {

    public static final String MESSAGE_CONSTRAINTS = "Routine names should be in words";
    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String routineName;
    public final Set<Exercise> exercises;

    /**
     * Constructs a {@code Routine}.
     *
     * @param routineName A valid routine name.
     */
    public Routine(String routineName) {
        requireNonNull(routineName);
        checkArgument(isValidRoutineName(routineName), MESSAGE_CONSTRAINTS);
        exercises = new HashSet<>();
        this.routineName = routineName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidRoutineName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Routine // instanceof handles nulls
                && routineName.equals(((Routine) other).routineName)); // state check
    }

    @Override
    public int hashCode() {
        return routineName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + routineName + ']';
    }
}
