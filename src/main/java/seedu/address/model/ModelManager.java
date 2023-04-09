package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final VersionedAddressBook versionedAddressBook;
    private Index selectedClientIndex;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        versionedAddressBook = new VersionedAddressBook(this.addressBook);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        selectedClientIndex = Index.fromZeroBased(0);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        commit();
    }

    /**
     * Overload
     * This version doesn't do commit
     *
     * @param addressBook
     */
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook.resetData(addressBook.clone());
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    //=========== Versioned Address Book =====================================================================
    public void commit() {
        versionedAddressBook.commit(addressBook);
    }

    public boolean canUndo() {
        return versionedAddressBook.canUndo();
    }

    public boolean canRedo() {
        return versionedAddressBook.canRedo();
    }

    /**
     * Undo and checkout the version of AddressBook that we want
     */
    public void undo() {
        AddressBook ab = versionedAddressBook.undo();
        setAddressBook(ab);
    }

    /**
     * Redo and checkout the version of AddressBook that we want
     */
    public void redo() {
        AddressBook ab = versionedAddressBook.redo();
        setAddressBook(ab);
    }

    //===============Sort ================================================================================
    @Override
    public void sort(List<Client> sortList) {
        addressBook.setClients(sortList);
        commit();
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
        commit();
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        commit();
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        addressBook.setClient(target, editedClient);
        commit();
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
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
    public ObservableList<Policy> getFilteredPolicyList() {
        Client selectedClient = getSelectedClient();

        if (selectedClient.equals(new Client())) {
            return FXCollections.observableArrayList();
        }
        return selectedClient.getFilteredPolicyList();
    }


    /**
     * Returns the selected Client
     */
    public Client getSelectedClient() {
        ObservableList<Client> updatedClientList = this.addressBook.getClientList();
        if (selectedClientIndex.getOneBased() > updatedClientList.size()) {
            return new Client();
        }
        return updatedClientList.get(selectedClientIndex.getZeroBased());
    }

    /**
     * Returns the selected Client index
     */
    public int getSelectedClientIndex() {
        return this.selectedClientIndex.getOneBased();
    }

    /**
     * Sets the selected Client
     */
    @Override
    public void setSelectedClientIndex(Index targetIndex) {
        this.selectedClientIndex = targetIndex;
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
        return addressBook.equals(other.addressBook)
            && userPrefs.equals(other.userPrefs);
        //&& filteredClients.equals(other.filteredClients);
    }

}
