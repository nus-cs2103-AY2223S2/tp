package seedu.fitbook.testutil.routine;

import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_SITUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_STRENGTH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.routines.Routine;

/**
 * A utility class containing a list of {@code Routine} objects to be used in tests.
 */
public class TypicalRoutines {

    public static final Routine JUMP = new RoutineBuilder().withRoutineName("Jumps")
            .withExercises("3x10 Jumping Jacks", "4x5 1km Run").build();
    public static final Routine STRENGTH = new RoutineBuilder().withRoutineName("Strength")
            .withExercises("3x10 Dumbbell Curls", "4x5 Bench Press").build();
    public static final Routine LEGS = new RoutineBuilder().withRoutineName("Legs Exercises")
            .withExercises("3x10 Squats", "4x5 Deadlift", "5x10 10kg forward lunges").build();

    // Manually added
    public static final Routine HIIT = new RoutineBuilder().withRoutineName("High intensity interval training")
            .withExercises("3x10mins leg raise", "4x5mins plank").build();
    public static final Routine ARMS = new RoutineBuilder().withRoutineName("Arms training")
            .withExercises("3x10 Push ups", "4x5 Dumbbell curls", "2x100 Pull ups").build();

    // Manually added - Routine's details found in {@code CommandTestUtil}
    public static final Routine CARDIO = new RoutineBuilder().withRoutineName(VALID_ROUTINE_NAME_CARDIO)
            .withExercises(VALID_EXERCISE_PUSHUP).build();
    public static final Routine STR = new RoutineBuilder().withRoutineName(VALID_ROUTINE_NAME_STRENGTH)
            .withExercises(VALID_EXERCISE_PUSHUP, VALID_EXERCISE_SITUP).build();

    private TypicalRoutines() {} // prevents instantiation

    /**
     * Returns an {@code FitBookExerciseRoutine} with all the typical routines.
     */
    public static FitBookExerciseRoutine getTypicalFitBookExerciseRoutine() {
        FitBookExerciseRoutine er = new FitBookExerciseRoutine();
        for (Routine routine : getTypicalRoutines()) {
            er.addRoutine(routine);
        }
        return er;
    }

    public static List<Routine> getTypicalRoutines() {
        return new ArrayList<>(Arrays.asList(JUMP, STRENGTH, LEGS));
    }
}
