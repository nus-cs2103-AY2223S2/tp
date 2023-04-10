package seedu.techtrack.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.logic.parser.OrderParser;
import seedu.techtrack.model.role.Role;

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
     * Returns the user prefs' role book file path.
     */
    Path getRoleBookFilePath();

    /**
     * Sets the user prefs' role book file path.
     */
    void setRoleBookFilePath(Path roleBookFilePath);

    /**
     * Replaces role book data with the data in {@code roleBook}.
     */
    void setRoleBook(ReadOnlyRoleBook roleBook);

    /** Returns the RoleBook */
    ReadOnlyRoleBook getRoleBook();

    /**
     * Returns true if a role with the same identity as {@code role} exists in the role book.
     */
    boolean hasRole(Role role);

    /**
     * Deletes the given role.
     * The role must exist in the role book.
     */
    void deleteRole(Role target);

    /**
     * Adds the given role.
     * {@code role} must not already exist in the role book.
     */
    void addRole(Role role);

    /**
     * Replaces the given role {@code target} with {@code editedRole}.
     * {@code target} must exist in the role book.
     * The role identity of {@code editedRole} must not be the same as another existing role in the role book.
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
    void displaySortedSalaryList(OrderParser orderParser);

    /**
     * Display the DeadlineList.
     * @throws NullPointerException if {@code list} is null.
     */
    void displaySortedDeadlineList(OrderParser orderParser);

}
