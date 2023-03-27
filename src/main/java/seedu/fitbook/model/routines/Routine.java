package seedu.fitbook.model.routines;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a Routine in the FitBook.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Routine {

    private final RoutineName routineName;
    private final List<Exercise> exercises = new ArrayList<>();

    /**
     * Constructs a {@code Routine}.
     *
     * @param routineName A valid routine name.
     */
    public Routine(RoutineName routineName, List<Exercise> exercises) {
        requireNonNull(routineName);
        this.exercises.addAll(exercises);
        this.routineName = routineName;
    }

    /**
     * Sets exercises for {@code exercises}.
     *
     * @param strings Strings of exercises to be stored in object.
     */
    public void withExercises(String... strings) {
        List<Exercise> exercises = Arrays.stream(strings)
                .map(Exercise::new)
                .collect(Collectors.toList());
        this.exercises.clear();
        this.exercises.addAll(exercises);
    }

    public RoutineName getRoutineName() {
        return routineName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<String> getExercisesName() {
        List<String> exercisesName = new ArrayList<>();
        exercises.forEach(exercise -> exercisesName.add(exercise.exerciseName));
        return exercisesName;
    }

    /**
     * Returns a boolean to check if another {@code RoutineName} is the same as the {@code routineName} in this
     * instance object.
     */
    public boolean isSameRoutineName(Routine otherRoutine) {
        return routineName.equals(otherRoutine.getRoutineName());
    }

    /**
     * Returns true if both routines have the same identity and data fields.
     * This defines a stronger notion of equality between two routines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Routine)) {
            return false;
        }

        Routine otherRoutine = (Routine) other;
        return otherRoutine.getRoutineName().equals(getRoutineName())
                && otherRoutine.getExercises().equals(getExercises());
    }

    /**
     * Returns true if both routines have the same name.
     * This defines a weaker notion of equality between two routines.
     */
    public boolean isSameRoutine(Routine otherRoutine) {
        if (otherRoutine == this) {
            return true;
        }

        return otherRoutine != null
                && otherRoutine.getRoutineName().equals(getRoutineName());
    }

    public void deleteExercise(int targetIndex) {
        this.exercises.remove(targetIndex);
    }

    public void addExercise(Exercise exercise) {
        this.getExercises().add(exercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineName);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + getRoutineName() + "]");
        return builder.toString();
    }

    /**
     * Format exercise as text for viewing.
     * @return textString
     */
    public StringBuilder exerciseListToString() {
        StringBuilder textString = new StringBuilder();
        for (int i = 1; i <= exercises.size(); i++) {
            textString.append("    " + i + ". " + exercises.get(i - 1) + "\n");
        }
        return textString;
    }
}
