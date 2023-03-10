package seedu.address.testutil.routine;

import seedu.address.model.FitBookExerciseRoutine;
import seedu.address.model.routines.Routine;

/**
 * A utility class to help with building FitBookExerciseRoutine objects.
 * Example usage: <br>
 *     {@code FitBookExerciseRoutine ab = new FitBookExerciseRoutineBuilder()
 *     .withRoutine("Strength", "training").build();}
 */
public class FitBookExerciseRoutineBuilder {

    private FitBookExerciseRoutine fitBookExerciseRoutine;

    public FitBookExerciseRoutineBuilder() {
        fitBookExerciseRoutine = new FitBookExerciseRoutine();
    }

    public FitBookExerciseRoutineBuilder(FitBookExerciseRoutine fitBookExerciseRoutine) {
        this.fitBookExerciseRoutine = fitBookExerciseRoutine;
    }

    /**
     * Adds a new {@code Routine} to the {@code FitBookExerciseRoutine} that we are building.
     */
    public FitBookExerciseRoutineBuilder withRoutine(Routine routine) {
        fitBookExerciseRoutine.addRoutine(routine);
        return this;
    }

    public FitBookExerciseRoutine build() {
        return fitBookExerciseRoutine;
    }
}
