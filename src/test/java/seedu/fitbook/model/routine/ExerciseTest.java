package seedu.fitbook.model.routine;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void test_equalsSymmetric() {
        Exercise exerciseA = new Exercise("push up");
        Exercise exerciseB = new Exercise("push up");
        assertTrue(exerciseA.equals(exerciseB) && exerciseB.equals(exerciseA));
        assertTrue(exerciseA.hashCode() == exerciseB.hashCode());
    }
}
