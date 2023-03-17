package seedu.socket.model;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.socket.commons.core.GuiSettings;
import seedu.socket.commons.core.LogsCenter;
import seedu.socket.model.person.Person;

/**
 * Represents the in-memory model of the {@code Socket} data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Socket socket;
    private final VersionedSocket versionedSocket;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;

    private FilteredList<Person> viewedPerson;

    /**
     * Initializes a {@code ModelManager} with the given {@code ReadOnlySocket} socket
     * and {@code ReadOnlyUserPrefs} userPrefs.
     */
    public ModelManager(ReadOnlySocket socket, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(socket, userPrefs);

        logger.fine("Initializing with address book: " + socket + " and user prefs " + userPrefs);

        this.socket = new Socket(socket);
        this.versionedSocket = new VersionedSocket(this.socket);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.socket.getPersonList());
        viewedPerson = new FilteredList<>(this.socket.getPersonList());
        viewedPerson.setPredicate(x -> false);
    }

    public ModelManager() {
        this(new Socket(), new UserPrefs());
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
    public Path getSocketFilePath() {
        return userPrefs.getSocketFilePath();
    }

    @Override
    public void setSocketFilePath(Path socketFilePath) {
        requireNonNull(socketFilePath);
        userPrefs.setSocketFilePath(socketFilePath);
    }

    //=========== Socket ================================================================================

    @Override
    public void setSocket(ReadOnlySocket socket) {
        this.socket.resetData(socket);
    }

    @Override
    public ReadOnlySocket getSocket() {
        return socket;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return socket.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        socket.removePerson(target);
    }

    @Override
    public boolean hasDeleteMultiplePerson(Predicate<Person> predicate) {
        requireNonNull(predicate);
        return socket.removeAllPerson(predicate);
    }

    @Override
    public void addPerson(Person person) {
        socket.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        socket.setPerson(target, editedPerson);
    }

    @Override
    public void commitSocket() {
        versionedSocket.commit(socket);
    }

    @Override
    public void undoSocket() {
        versionedSocket.undo();
    }

    @Override
    public void redoSocket() {
        versionedSocket.redo();
    }

    @Override
    public boolean canUndoSocket() {
        return versionedSocket.canUndoSocket();
    }

    @Override
    public boolean canRedoSocket() {
        return versionedSocket.canRedoSocket();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedSocket}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getViewedPerson() {
        return viewedPerson;
    }

    @Override
    public void updateViewedPerson(Person person) {
        viewedPerson.setPredicate(x -> x.isSamePerson(person));
    }

    @Override
    public void sortPersonList(String category) {
        socket.sort(category);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return socket.equals(other.socket)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && viewedPerson.equals(other.viewedPerson);
    }

}
