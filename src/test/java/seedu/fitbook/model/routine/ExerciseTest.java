package seedu.fitbook.model.routine;

import static seedu.fitbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.Exercise;

public class ExerciseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Exercise(null));
    }

    @Test
    public void constructor_invalidExerciseName_throwsIllegalArgumentException() {
        String invalidExerciseName = "";
        assertThrows(IllegalArgumentException.class, () -> new Exercise(invalidExerciseName));
    }

    @Test
    public void isValidExerciseName() {
        // null exercise name
        assertThrows(NullPointerException.class, () -> Exercise.isValidExerciseName(null));
    }

}
