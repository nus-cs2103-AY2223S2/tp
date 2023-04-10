package seedu.fitbook.model.routine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_SITUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_STRENGTH;
import static seedu.fitbook.testutil.routine.TypicalRoutines.CARDIO;
import static seedu.fitbook.testutil.routine.TypicalRoutines.STRENGTH;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.testutil.routine.RoutineBuilder;

public class RoutineTest {

    @Test
    public void isSameRoutine() {
        // same object -> returns true
        assertTrue(CARDIO.isSameRoutine(CARDIO));

        // null -> returns false
        assertFalse(CARDIO.isSameRoutine(null));

        // same routine name, all other attributes different -> returns true
        Routine editedCardio = new RoutineBuilder(CARDIO).withExercises(VALID_EXERCISE_PUSHUP).build();
        assertTrue(CARDIO.isSameRoutine(editedCardio));

        // different routine name, all other attributes same -> returns false
        editedCardio = new RoutineBuilder(CARDIO).withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        assertFalse(CARDIO.isSameRoutine(editedCardio));

        // routine name differs in case, all other attributes same -> returns false
        Routine editedStrength = new RoutineBuilder(STRENGTH).withRoutineName(VALID_ROUTINE_NAME_STRENGTH.toLowerCase())
                .build();
        assertFalse(STRENGTH.isSameRoutine(editedStrength));

        // routine name has trailing spaces, all other attributes same -> returns false
        String routineNameWithTrailingSpaces = VALID_ROUTINE_NAME_STRENGTH + " ";
        editedStrength = new RoutineBuilder(STRENGTH).withRoutineName(routineNameWithTrailingSpaces).build();
        assertFalse(STRENGTH.isSameRoutine(editedStrength));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Routine cardioCopy = new RoutineBuilder(CARDIO).build();
        assertTrue(CARDIO.equals(cardioCopy));

        // same object -> returns true
        assertTrue(CARDIO.equals(CARDIO));

        // null -> returns false
        assertFalse(CARDIO.equals(null));

        // different type -> returns false
        assertFalse(CARDIO.equals(5));

        // different routine -> returns false
        assertFalse(CARDIO.equals(STRENGTH));

        // different routine name -> returns false
        Routine editedCardio = new RoutineBuilder(CARDIO).withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        assertFalse(CARDIO.equals(editedCardio));

        // different exercises -> returns false
        editedCardio = new RoutineBuilder().withRoutineName(VALID_ROUTINE_NAME_CARDIO)
                .withExercises(VALID_EXERCISE_SITUP).build();
        assertFalse(CARDIO.equals(editedCardio));
    }
    @Test
    public void test_equalsSymmetric() {
        Routine routineA = new RoutineBuilder(CARDIO).withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        Routine routineCopyA = new RoutineBuilder(CARDIO).withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        assertTrue(routineA.equals(routineCopyA) && routineCopyA.equals(routineA));
        assertTrue(routineA.hashCode() == routineCopyA.hashCode());
    }
}
