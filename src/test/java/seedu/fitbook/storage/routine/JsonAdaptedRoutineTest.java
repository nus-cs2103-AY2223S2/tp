package seedu.fitbook.storage.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbook.storage.routine.JsonAdaptedRoutine.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.routine.TypicalRoutines.STRENGTH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.routines.RoutineName;

public class JsonAdaptedRoutineTest {
    private static final String INVALID_ROUTINE_NAME = "R@chel";
    private static final String INVALID_EXERCISE = "af-15-2142";

    private static final String VALID_ROUTINE_NAME = STRENGTH.getRoutineName().toString();

    private static final List<JsonAdaptedExercise> VALID_EXERCISES = STRENGTH.getExercises().stream()
            .map(JsonAdaptedExercise::new)
            .collect(Collectors.toList());

    @Test
    public void toFitBookModelType_validRoutineDetails_returnsRoutine() throws Exception {
        JsonAdaptedRoutine routine = new JsonAdaptedRoutine(STRENGTH);
        assertEquals(STRENGTH, routine.toFitBookExerciseRoutineModelType());
    }

    @Test
    public void toFitBookExerciseRoutineModelType_invalidRoutineName_throwsIllegalValueException() {
        JsonAdaptedRoutine routine =
                new JsonAdaptedRoutine(INVALID_ROUTINE_NAME, VALID_EXERCISES);
        String expectedMessage = RoutineName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, routine::toFitBookExerciseRoutineModelType);
    }

    @Test
    public void toFitBookExerciseRoutineModelType_nullRoutineName_throwsIllegalValueException() {
        JsonAdaptedRoutine routine = new JsonAdaptedRoutine(null, VALID_EXERCISES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoutineName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, routine::toFitBookExerciseRoutineModelType);
    }

    @Test
    public void toFitBookExerciseRoutineModelType_invalidExercises_throwsIllegalValueException() {
        List<JsonAdaptedExercise> invalidAppointments = new ArrayList<>(VALID_EXERCISES);
        invalidAppointments.add(new JsonAdaptedExercise(INVALID_EXERCISE));
        JsonAdaptedRoutine routine =
                new JsonAdaptedRoutine(VALID_ROUTINE_NAME, invalidAppointments);
        assertThrows(IllegalValueException.class, routine::toFitBookExerciseRoutineModelType);
    }
}
