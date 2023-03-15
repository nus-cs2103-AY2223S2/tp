package seedu.fitbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;

/**
 * Represents the in-memory model of the FitBook data.
 */
public class FitBookModelManager implements FitBookModel {
    private static final Logger logger = LogsCenter.getLogger(FitBookModelManager.class);

    private final FitBook fitBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Routine> filteredRoutines;
    private final FitBookExerciseRoutine fitBookExerciseRoutine;

    /**
     * Initializes a FitBookModelManager with the given fitBook and userPrefs.
     */
    public FitBookModelManager(ReadOnlyFitBook fitBook, ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine,
                               ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(fitBook, fitBookExerciseRoutine, userPrefs);

        logger.fine("Initializing with FitBook: " + fitBook + " , exercises routines "
                + fitBookExerciseRoutine + " and user prefs " + userPrefs);

        this.fitBook = new FitBook(fitBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.fitBookExerciseRoutine = new FitBookExerciseRoutine(fitBookExerciseRoutine);
        filteredRoutines = new FilteredList<>(this.fitBookExerciseRoutine.getRoutineList());
        filteredClients = new FilteredList<>(this.fitBook.getClientList());
    }

    public FitBookModelManager() {
        this(new FitBook(), new FitBookExerciseRoutine(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFitBookFilePath() {
        return userPrefs.getFitBookFilePath();
    }

    @Override
    public void setFitBookFilePath(Path fitBookFilePath) {
        requireNonNull(fitBookFilePath);
        userPrefs.setFitBookFilePath(fitBookFilePath);
    }

    //=========== FitBook ================================================================================

    @Override
    public void setFitBook(ReadOnlyFitBook addressBook) {
        this.fitBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyFitBook getFitBook() {
        return fitBook;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return fitBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        fitBook.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        fitBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        fitBook.setClient(target, editedClient);
    }

    //=========== Exercise Routines ================================================================================

    @Override
    public void setFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine exerciseRoutine) {
        this.fitBookExerciseRoutine.resetData(exerciseRoutine);
    }

    @Override
    public ReadOnlyFitBookExerciseRoutine getFitBookExerciseRoutine() {
        return fitBookExerciseRoutine;
    }

    @Override
    public boolean hasRoutine(Routine routine) {
        requireNonNull(routine);
        return fitBookExerciseRoutine.hasRoutine(routine);
    }

    @Override
    public void deleteRoutine(Routine target) {
        fitBookExerciseRoutine.removeRoutine(target);
    }

    @Override
    public void addRoutine(Routine routine) {
        fitBookExerciseRoutine.addRoutine(routine);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
    }

    @Override
    public void setRoutine(Routine target, Routine editedRoutine) {
        requireAllNonNull(target, editedRoutine);
        fitBookExerciseRoutine.setRoutine(target, editedRoutine);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedFitBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Routine} backed by the internal list of
     * {@code versionedFitBookExerciseRoutine}
     */
    @Override
    public ObservableList<Routine> getFilteredRoutineList() {
        return filteredRoutines;
    }

    @Override
    public void updateFilteredRoutineList(Predicate<Routine> predicate) {
        requireNonNull(predicate);
        filteredRoutines.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof FitBookModelManager)) {
            return false;
        }

        // state check
        FitBookModelManager other = (FitBookModelManager) obj;
        return fitBook.equals(other.fitBook)
                && fitBookExerciseRoutine.equals(other.fitBookExerciseRoutine)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredRoutines.equals(other.filteredRoutines);
    }

}
