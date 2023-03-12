package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;

/**
 * Represents the in-memory model of the address book data.
 */
public class FitBookModelManager implements FitBookModel {
    private static final Logger logger = LogsCenter.getLogger(FitBookModelManager.class);

    private final FitBook fitBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;

    /**
     * Initializes a FitBookModelManager with the given fitBook and userPrefs.
     */
    public FitBookModelManager(ReadOnlyFitBook fitBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(fitBook, userPrefs);

        logger.fine("Initializing with address book: " + fitBook + " and user prefs " + userPrefs);

        this.fitBook = new FitBook(fitBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.fitBook.getClientList());
    }

    public FitBookModelManager() {
        this(new FitBook(), new UserPrefs());
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
    public void setFitBook(ReadOnlyFitBook fitBook) {
        this.fitBook.resetData(fitBook);
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
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
