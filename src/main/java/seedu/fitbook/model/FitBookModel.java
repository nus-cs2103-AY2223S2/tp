package seedu.fitbook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;

/**
 * The API of the FitBookModel component.
 */
public interface FitBookModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Routine> PREDICATE_SHOW_ALL_ROUTINES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' FitBook file path.
     */
    Path getFitBookFilePath();

    /**
     * Sets the user prefs' FitBook file path.
     */
    void setFitBookFilePath(Path fitBookFilePath);

    /**
     * Replaces FitBook data with the data in {@code fitBook}.
     */
    void setFitBook(ReadOnlyFitBook fitBook);

    /**
     * Returns the FitBook
     */
    ReadOnlyFitBook getFitBook();

    /**
     * Returns true if a client with the same identity as {@code client} exists in FitBook.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in FitBook.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in FitBook.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the FitBook.
     * The client identity of {@code editedClient} must not be the same as another existing client in FitBook.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Returns an unmodifiable view of the filtered client list
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Replaces exercise routine data with the data in {@code exerciseRoutine}.
     */
    void setFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine exerciseRoutine);

    /** Returns the Exercise Routine */
    ReadOnlyFitBookExerciseRoutine getFitBookExerciseRoutine();

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in the exercise routine.
     */
    boolean hasRoutine(Routine routine);

    /**
     * Deletes the given routine.
     * The routine must exist in the exercise routine.
     */
    void deleteRoutine(Routine target);

    /**
     * Adds the given routine.
     * {@code routine} must not already exist in the exercise routine.
     */
    void addRoutine(Routine routine);

    void addExercise(Routine routine, Exercise exercise);

    /**
     * Replaces the given routine {@code target} with {@code editedRoutine}.
     * {@code target} must exist in the exercise routine.
     * The routine identity of {@code editedRoutine} must not be the same
     * as another existing routine in the exercise routine.
     */
    void setRoutine(Routine target, Routine editedRoutine);

    /** Returns an unmodifiable view of the filtered routine list */
    ObservableList<Routine> getFilteredRoutineList();

    /**
     * Updates the filter of the filtered routine list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoutineList(Predicate<Routine> predicate);

    void removeExercise(Routine routineToDelete, int zeroBased);

}
