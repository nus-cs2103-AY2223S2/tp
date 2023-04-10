package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Returns an unmodifiable view of the filtered client list
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns an unmodifiable view of the filtered policy list of the selected client
     */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Returns the selected Client
     */
    Client getSelectedClient();

    /**
     * Returns the selected Client index
     */
    int getSelectedClientIndex();

    /**
     * Sets the selected Client
     */
    void setSelectedClientIndex(Index targetIndex);

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Returns whether the {@code model} has an available redo state.
     *
     * @return true if the model can redo, else false
     */
    boolean canRedo();

    /**
     * Commits the changes to the cached state.
     */
    void commit();

    /**
     * Returns whether the {@code model} has an available undo state.
     *
     * @return true if the model can undo, else false
     */
    boolean canUndo();

    /**
     * Changes the {@code model} to the next saved state.
     */
    void redo();

    /**
     * Changes the {@code model} to the previous saved state.
     */
    void undo();

    /**
     * Sorts the {@code sortList}.
     *
     * @param sortList
     */
    void sort(List<Client> sortList);
}
