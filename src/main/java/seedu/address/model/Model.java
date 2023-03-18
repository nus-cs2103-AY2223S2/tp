package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.job.Order;
import seedu.address.model.job.Role;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Role> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a role with the same identity as {@code role} exists in the address book.
     */
    boolean hasRole(Role role);

    /**
     * Deletes the given role.
     * The role must exist in the address book.
     */
    void deleteRole(Role target);

    /**
     * Adds the given role.
     * {@code role} must not already exist in the address book.
     */
    void addRole(Role role);

    /**
     * Replaces the given role {@code target} with {@code editedRole}.
     * {@code target} must exist in the address book.
     * The role identity of {@code editedRole} must not be the same as another existing role in the address book.
     */
    void setRole(Role target, Role editedRole);

    /** Returns an unmodifiable view of the filtered role list */
    ObservableList<Role> getFilteredRoleList();

    /**
     * Updates the filter of the filtered role list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoleList(Predicate<Role> predicate);

    /**
     * Display the RoleList.
     * @throws NullPointerException if {@code list} is null.
     */
    void displaySortedRoleList(Order order);
}
