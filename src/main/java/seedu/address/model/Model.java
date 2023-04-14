package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Employee> PREDICATE_SHOW_ALL_EMPLOYEES = unused -> true;

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
     * Returns the user prefs' ExecutivePro file path.
     */
    Path getExecutiveProFilePath();

    /**
     * Sets the user prefs' ExecutivePro file path.
     */
    void setExecutiveProFilePath(Path executiveProFilePath);

    /**
     * Replaces ExecutivePro data with the data in {@code executiveProDb}.
     */
    void setExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb);

    /** Returns the ExecutiveProDb */
    ReadOnlyExecutiveProDb getExecutiveProDb();

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in ExecutivePro.
     */
    boolean hasEmployee(Employee employee);

    /**
     * Returns an Optional object that may contain the employee with a given employee ID {@code employeeId}.
     */
    Optional<Employee> getEmployee(EmployeeId employeeId);

    /**
     * Deletes the given employee.
     * The employee must exist in ExecutivePro.
     */
    void deleteEmployee(Employee target);

    /**
     * Adds the given employee.
     * {@code employee} must not already exist in ExecutivePro.
     */
    void addEmployee(Employee employee);

    /**
     * Batch Adds the employees from the user input.
     * {@code fileName} must not already exist in ExecutivePro.
     */
    void batchAddEmployees(String fileName);

    /**
     * Replaces the given employee {@code target} with {@code editedEmployee}.
     * {@code target} must exist in the ExecutivePro.
     * The employee identity of {@code editedPerson} must not be the same as another
     * existing employee in the ExecutivePro.
     */
    void setEmployee(Employee target, Employee editedEmployee);

    /** Returns an unmodifiable view of the full employee list */
    ObservableList<Employee> getFullEmployeeList();

    /** Returns an unmodifiable view of the filtered employee list */
    ObservableList<Employee> getFilteredEmployeeList();

    /**
     * Updates the filter of the filtered employee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEmployeeList(Predicate<Employee> predicate);
}
