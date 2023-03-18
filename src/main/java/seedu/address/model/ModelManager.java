package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.job.Order;
import seedu.address.model.job.Role;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Role> filteredRoles;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredRoles = new FilteredList<>(this.addressBook.getRoleList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasRole(Role role) {
        requireNonNull(role);
        return addressBook.hasRole(role);
    }

    @Override
    public void deleteRole(Role target) {
        addressBook.removeRole(target);
    }

    @Override
    public void addRole(Role role) {
        addressBook.addRole(role);
        updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setRole(Role target, Role editedRole) {
        requireAllNonNull(target, editedRole);

        addressBook.setRole(target, editedRole);
    }

    //=========== Filtered Role List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Role} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Role> getFilteredRoleList() {
        return filteredRoles;
    }

    @Override
    public void updateFilteredRoleList(Predicate<Role> predicate) {
        requireNonNull(predicate);
        filteredRoles.setPredicate(predicate);
    }

    @Override
    public void displaySortedRoleList(Order order) {
        List<Role> roles = filteredRoles.sorted((r1, r2) -> {
            int s1 = Integer.parseInt(r1.getSalary().toString());
            int s2 = Integer.parseInt(r2.getSalary().toString());
            if (order.toString().equals("asc")) {
                return Integer.compare(s1, s2);
            } else {
                return Integer.compare(s2, s1);
            }
        });
        this.addressBook.setRoles(roles);
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
                && userPrefs.equals(other.userPrefs)
                && filteredRoles.equals(other.filteredRoles);
    }

}
