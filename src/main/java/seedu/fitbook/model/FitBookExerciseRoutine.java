package seedu.fitbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.UniqueRoutineList;

/**
 * Wraps all data at the fit-book level
 * Duplicates are not allowed (by .isSameRoutine comparison)
 */
public class FitBookExerciseRoutine implements ReadOnlyFitBookExerciseRoutine {

    private final UniqueRoutineList routines;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        routines = new UniqueRoutineList();
    }

    public FitBookExerciseRoutine() {}

    /**
     * Creates an FitBook using the Routines in the {@code toBeCopied}
     */
    public FitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the routine list with {@code routines}.
     * {@code routines} must not contain duplicate routines.
     */
    public void setRoutines(List<Routine> routines) {
        this.routines.setRoutines(routines);
    }

    /**
     * Resets the existing data of this {@code FitBookExerciseRoutine} with {@code newData}.
     */
    public void resetData(ReadOnlyFitBookExerciseRoutine newData) {
        requireNonNull(newData);

        setRoutines(newData.getRoutineList());
    }

    //// routine-level operations

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in the address book.
     */
    public boolean hasRoutine(Routine routine) {
        requireNonNull(routine);
        return routines.contains(routine);
    }

    /**
     * Adds a routine to the FitBook exercise routine.
     * The routine must not already exist in the FitBook exercise routine.
     */
    public void addRoutine(Routine p) {
        routines.add(p);
    }

    /**
     * Replaces the given routine {@code target} in the list with {@code editedRoutine}.
     * {@code target} must exist in the FitBook exercise routine.
     * The routine identity of {@code editedRoutine} must not be the same
     * as another existing routine in the FitBook exercise routine.
     */
    public void setRoutine(Routine target, Routine editedRoutine) {
        requireNonNull(editedRoutine);

        routines.setRoutine(target, editedRoutine);
    }

    /**
     * Removes {@code key} from this {@code FitBookExerciseRoutine}.
     * {@code key} must exist in the FitBook exercise routine.
     */
    public void removeRoutine(Routine key) {
        routines.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return routines.asUnmodifiableObservableList().size() + " routines";
        // TODO: refine later
    }

    @Override
    public ObservableList<Routine> getRoutineList() {
        return routines.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FitBookExerciseRoutine // instanceof handles nulls
                && routines.equals(((FitBookExerciseRoutine) other).routines));
    }

    @Override
    public int hashCode() {
        return routines.hashCode();
    }
}
