package seedu.fitbook.model.routines;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

/**
 * Represents an Exercise in the FitBook Exercise Routine.
 * Guarantees: immutable; name is valid as declared in {@link #isValidExerciseName(String)}
 */
public class Exercise {
    public static final String MESSAGE_CONSTRAINTS = "Exercises names should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]*$";

    public final String exerciseName;

    /**
     * Constructs a {@code Exercise}.
     *
     * @param exerciseName A valid tag name.
     */
    public Exercise(String exerciseName) {
        requireNonNull(exerciseName);
        checkArgument(isValidExerciseName(exerciseName), MESSAGE_CONSTRAINTS);
        this.exerciseName = exerciseName;
    }

    /**
     * Returns true if a given string is a valid exercise name.
     */
    public static boolean isValidExerciseName(String test) {
        return test.matches(VALIDATION_REGEX)
                && !test.trim().isEmpty()
                && !test.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Exercise // instanceof handles nulls
                && exerciseName.equals(((Exercise) other).exerciseName)); // state check
    }

    @Override
    public int hashCode() {
        return exerciseName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + exerciseName + ']';
    }

}
