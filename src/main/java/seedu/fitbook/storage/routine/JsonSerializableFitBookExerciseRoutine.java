package seedu.fitbook.storage.routine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.routines.Routine;

/**
 * An Immutable FitBook Exercise Routine that is serializable to JSON format.
 */
@JsonRootName(value = "routinebook")
class JsonSerializableFitBookExerciseRoutine {

    public static final String MESSAGE_DUPLICATE_ROUTINE = "Exercise Routine list contains duplicate routine(s).";

    private final List<JsonAdaptedRoutine> routines = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFitBookExerciseRoutine} with the given routines.
     */
    @JsonCreator
    public JsonSerializableFitBookExerciseRoutine(@JsonProperty("routines") List<JsonAdaptedRoutine> routines) {
        this.routines.addAll(routines);
    }

    /**
     * Converts a given {@code ReadOnlyFitBookExerciseRoutine} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFitBookExerciseRoutine}.
     */
    public JsonSerializableFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine source) {
        routines.addAll(source.getRoutineList().stream().map(JsonAdaptedRoutine::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Fitbook into the model's {@code FitBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FitBookExerciseRoutine toFitBookExerciseRoutineModelType() throws IllegalValueException {
        FitBookExerciseRoutine fitBookExerciseRoutine = new FitBookExerciseRoutine();
        for (JsonAdaptedRoutine jsonAdaptedRoutine : routines) {
            Routine routine = jsonAdaptedRoutine.toFitBookExerciseRoutineModelType();
            if (fitBookExerciseRoutine.hasRoutine(routine)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROUTINE);
            }
            fitBookExerciseRoutine.addRoutine(routine);
        }
        return fitBookExerciseRoutine;
    }

}
