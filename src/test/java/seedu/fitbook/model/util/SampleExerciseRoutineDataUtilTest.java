package seedu.fitbook.model.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.fitbook.model.util.SampleExerciseRoutineDataUtil.getExerciseList;
import static seedu.fitbook.testutil.Assert.assertArrayNotEquals;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

public class SampleExerciseRoutineDataUtilTest {

    @Test
    public void getSampleRoutines() {
        Routine[] testRoutine = new Routine[] {
            new Routine(new RoutineName("OPM"), getExerciseList("100 Push ups", "100 Sit ups", "10km run")),
            new Routine(new RoutineName("Cardio"), getExerciseList("10 Berpes", "100 crunches")),
            new Routine(new RoutineName("Strength"),
                    getExerciseList("4x15 Dumbbell curls", "3x15 Bench Press", "5 reps of Deadlift"))
        };

        int routineNumber = SampleExerciseRoutineDataUtil.getSampleRoutines().length;
        Routine[] nullTestRoutine = new Routine[routineNumber];

        // Sample Exercise Routine Data is the equal to the sample data of test.
        assertArrayEquals(SampleExerciseRoutineDataUtil.getSampleRoutines(), testRoutine);

        // Sample Exercise Routine Data is non null.
        assertArrayNotEquals(nullTestRoutine, SampleExerciseRoutineDataUtil.getSampleRoutines());
    }
}
