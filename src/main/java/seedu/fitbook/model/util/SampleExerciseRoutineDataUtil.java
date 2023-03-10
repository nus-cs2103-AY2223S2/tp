package seedu.fitbook.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

/**
 * Contains utility methods for populating {@code FitBookExerciseRoutine} with sample data.
 */
public class SampleExerciseRoutineDataUtil {

    public static Routine[] getSampleRoutines() {
        return new Routine[] {
            new Routine(new RoutineName("OPM"), getExerciseList("100 Push ups", "100 Sit ups", "10km run")),
            new Routine(new RoutineName("Cardio"), getExerciseList("10 Berpes", "100 crunches")),
            new Routine(new RoutineName("Strength"),
                getExerciseList("4x15 Dumbbell curls", "3x15 Bench Press", "5 reps of Deadlift"))
        };
    }

    public static ReadOnlyFitBookExerciseRoutine getSampleFitBookExerciseRoutine() {
        FitBookExerciseRoutine sampleEr = new FitBookExerciseRoutine();
        for (Routine sampleRoutine : getSampleRoutines()) {
            sampleEr.addRoutine(sampleRoutine);
        }
        return sampleEr;
    }

    /**
     * Returns an Exercise set containing the list of strings given.
     */
    public static List<Exercise> getExerciseList(String... strings) {
        return Arrays.stream(strings)
                .map(Exercise::new)
                .collect(Collectors.toList());
    }

}
