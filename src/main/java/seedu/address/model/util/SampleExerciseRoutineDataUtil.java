package seedu.address.model.util;

import seedu.address.model.FitBookExerciseRoutine;
import seedu.address.model.ReadOnlyFitBookExerciseRoutine;
import seedu.address.model.routines.Exercise;
import seedu.address.model.routines.Routine;
import seedu.address.model.routines.RoutineName;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code FitBookExerciseRoutine} with sample data.
 */
public class SampleExerciseRoutineDataUtil {

    public static Routine[] getSampleRoutines() {
        return new Routine[] {
            new Routine(new RoutineName("OPM"), getExerciseSet("100 Push ups", "100 Sit ups", "10km run")),
            new Routine(new RoutineName("Cardio"), getExerciseSet("10 Berpes", "100 crunches")),
            new Routine(new RoutineName("Strength"),
                getExerciseSet("4x15 Dumbbell curls", "3x15 Bench Press", "5 reps of Deadlift"))
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
    public static Set<Exercise> getExerciseSet(String... strings) {
        return Arrays.stream(strings)
                .map(Exercise::new)
                .collect(Collectors.toSet());
    }

}
