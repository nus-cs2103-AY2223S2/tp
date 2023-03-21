package seedu.fitbook.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExerciseClient {

    private final List<String> exercises;

    /**
     * Constructs a {@code JsonAdaptedExerciseClient} with the given {@code exercise}.
     */
    @JsonCreator
    public JsonAdaptedExerciseClient(List<String> exercises) {
        this.exercises = exercises;
    }

    /**
     * Converts a given {@code Routine} into this class for Jackson use.
     */
    public JsonAdaptedExerciseClient(Routine source) {
        exercises = source.getExercisesName();
    }

    @JsonValue
    public List<String> getExercisesStr() {
        return exercises;
    }

    public List<Exercise> getExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        exercises.forEach(exercise -> exerciseList.add(new Exercise(exercise)));
        return exerciseList;
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public List<Exercise> toFitBookModelType() throws IllegalValueException {
        List<Exercise> exercisesList = new ArrayList<>();
        for (String exercise : exercises) {
            if (!Exercise.isValidExerciseName(exercise)) {
                throw new IllegalValueException(Exercise.MESSAGE_CONSTRAINTS);
            }
            exercisesList.add(new Exercise(exercise));
        }
        return exercisesList;
    }

}
