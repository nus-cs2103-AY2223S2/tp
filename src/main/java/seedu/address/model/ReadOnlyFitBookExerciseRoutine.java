package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.routines.Routine;

/**
 * Unmodifiable view of a FitBook routine book.
 */
public interface ReadOnlyFitBookExerciseRoutine {

    /**
     * Returns an unmodifiable view of the routines list.
     * This list will not contain any duplicate routines.
     */
    ObservableList<Routine> getRoutineList();

}
