package seedu.fitbook.storage.routine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.routines.Exercise;

/**
 * Jackson-friendly version of {@link seedu.fitbook.model.routines.Routine}.
 */
class JsonAdaptedExercise {

    private final String exerciseName;

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given {@code routineName}.
     */
    @JsonCreator
    public JsonAdaptedExercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        exerciseName = source.exerciseName;
    }

    @JsonValue
    public String getExerciseName() {
        return exerciseName;
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public Exercise toFitBookExerciseRoutineModelType() throws IllegalValueException {
        if (!Exercise.isValidExerciseName(exerciseName)) {
            throw new IllegalValueException(Exercise.MESSAGE_CONSTRAINTS);
        }
        return new Exercise(exerciseName);
    }

}
