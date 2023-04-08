package seedu.techtrack.model;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.commons.core.LogsCenter;
import seedu.techtrack.logic.parser.OrderParser;
import seedu.techtrack.model.role.Role;

/**
 * Represents the in-memory model of the role book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final RoleBook roleBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Role> filteredRoles;

    /**
     * Initializes a ModelManager with the given roleBook and userPrefs.
     */
    public ModelManager(ReadOnlyRoleBook roleBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(roleBook, userPrefs);

        logger.fine("Initializing with role book: " + roleBook + " and user prefs " + userPrefs);

        this.roleBook = new RoleBook(roleBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredRoles = new FilteredList<>(this.roleBook.getRoleList());
    }

    public ModelManager() {
        this(new RoleBook(), new UserPrefs());
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
    public Path getRoleBookFilePath() {
        return userPrefs.getRoleBookFilePath();
    }

    @Override
    public void setRoleBookFilePath(Path roleBookFilePath) {
        requireNonNull(roleBookFilePath);
        userPrefs.setRoleBookFilePath(roleBookFilePath);
    }

    //=========== RoleBook ================================================================================

    @Override
    public void setRoleBook(ReadOnlyRoleBook roleBook) {
        this.roleBook.resetData(roleBook);
    }

    @Override
    public ReadOnlyRoleBook getRoleBook() {
        return roleBook;
    }

    @Override
    public boolean hasRole(Role role) {
        requireNonNull(role);
        return roleBook.hasRole(role);
    }

    @Override
    public void deleteRole(Role target) {
        roleBook.removeRole(target);
    }

    @Override
    public void addRole(Role role) {
        roleBook.addRole(role);
        updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setRole(Role target, Role editedRole) {
        requireAllNonNull(target, editedRole);

        roleBook.setRole(target, editedRole);
    }

    //=========== Filtered Role List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Role} backed by the internal list of
     * {@code versionedRoleBook}
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
    public void displaySortedSalaryList(OrderParser orderParser) {
        ObservableList<Role> obsList = this.roleBook.getRoleList();
        List<Role> roles = obsList.sorted((r1, r2) -> {
            int s1 = Integer.parseInt(r1.getSalary().toString());
            int s2 = Integer.parseInt(r2.getSalary().toString());
            if (orderParser.toString().equals("asc")) {
                return Integer.compare(s1, s2);
            } else {
                return Integer.compare(s2, s1);
            }
        });
        logger.info("RoleBook is sorted: " + this.roleBook);

        this.roleBook.setRoles(roles);
    }

    @Override
    public void displaySortedDeadlineList(OrderParser orderParser) {
        ObservableList<Role> obsList = this.roleBook.getRoleList();
        List<Role> roles = obsList.sorted((r1, r2) -> {
            LocalDate s1 = LocalDate.parse(r1.getDeadline().toString());
            LocalDate s2 = LocalDate.parse(r2.getDeadline().toString());
            if (orderParser.toString().equals("asc")) {
                return s1.compareTo(s2);
            } else {
                return s2.compareTo(s1);
            }
        });

        logger.info("RoleBook is sorted: " + this.roleBook);

        this.roleBook.setRoles(roles);
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
        return roleBook.equals(other.roleBook)
                && userPrefs.equals(other.userPrefs)
                && filteredRoles.equals(other.filteredRoles);
    }

}
