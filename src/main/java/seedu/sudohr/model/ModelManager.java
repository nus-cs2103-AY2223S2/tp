package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;

/**
 * Represents the in-memory model of the sudohr book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SudoHr sudoHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredEmployees;
    private final FilteredList<Department> filteredDepartments;

    /**
     * Initializes a ModelManager with the given sudoHr and userPrefs.
     */
    public ModelManager(ReadOnlySudoHr sudoHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(sudoHr, userPrefs);

        logger.fine("Initializing with SudoHR: " + sudoHr + " and user prefs " + userPrefs);

        this.sudoHr = new SudoHr(sudoHr);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredEmployees = new FilteredList<>(this.sudoHr.getEmployeeList());
        filteredDepartments = new FilteredList<>(this.sudoHr.getDepartmentList());
    }

    public ModelManager() {
        this(new SudoHr(), new UserPrefs());
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
    public Path getSudoHrFilePath() {
        return userPrefs.getSudoHrFilePath();
    }

    @Override
    public void setSudoHrFilePath(Path sudoHrFilePath) {
        requireNonNull(sudoHrFilePath);
        userPrefs.setSudoHrFilePath(sudoHrFilePath);
    }

    //=========== SudoHr ================================================================================

    @Override
    public void setSudoHr(ReadOnlySudoHr sudoHr) {
        this.sudoHr.resetData(sudoHr);
    }

    @Override
    public ReadOnlySudoHr getSudoHr() {
        return sudoHr;
    }

    //=========== Employee-Level Operations ========================

    @Override
    public boolean hasEmployee(Employee person) {
        requireNonNull(person);
        return sudoHr.hasEmployee(person);
    }

    @Override
    public boolean hasEmployee(Employee person, Employee excludeFromCheck) {
        requireNonNull(person);
        requireAllNonNull(excludeFromCheck);
        return sudoHr.hasEmployee(person, excludeFromCheck);
    }

    @Override
    public boolean hasClashingEmail(Employee person) {
        requireNonNull(person);
        return sudoHr.hasClashingEmail(person);
    }

    @Override
    public boolean hasClashingEmail(Employee person, Employee excludeFromCheck) {
        requireNonNull(person);
        requireNonNull(excludeFromCheck);
        return sudoHr.hasClashingEmail(person, excludeFromCheck);
    }

    @Override
    public boolean hasClashingPhoneNumber(Employee person) {
        requireNonNull(person);
        return sudoHr.hasClashingPhoneNumber(person);
    }

    @Override
    public boolean hasClashingPhoneNumber(Employee person, Employee excludeFromCheck) {
        requireNonNull(person);
        requireNonNull(excludeFromCheck);
        return sudoHr.hasClashingPhoneNumber(person, excludeFromCheck);
    }

    @Override
    public void deleteEmployee(Employee target) {
        sudoHr.removeEmployee(target);
    }

    @Override
    public void addEmployee(Employee employee) {
        sudoHr.addEmployee(employee);
        updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
    }

    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        sudoHr.setEmployee(target, editedEmployee);
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedSudoHr}
     */
    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredEmployees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredEmployees.setPredicate(predicate);
    }

    //=========== Department-Level Operations ==========================================================================

    @Override
    public Department getDepartment(DepartmentName name) {
        return sudoHr.getDepartment(name);
    }

    @Override
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return sudoHr.hasDepartment(department);
    }

    @Override
    public void addDepartment(Department d) {
        sudoHr.addDepartment(d);
    }

    @Override
    public void setDepartment(Department target, Department editedDepartment) {
        sudoHr.setDepartment(target, editedDepartment);
    }

    @Override
    public void removeDepartment(Department key) {
        sudoHr.removeDepartment(key);
    }

    @Override
    public void addEmployeeToDepartment(Employee p, Department d) {
        sudoHr.addEmployeeToDepartment(p, d);
    }

    @Override
    public void removeEmployeeFromDepartment(Employee p, Department d) {
        sudoHr.removeEmployeeFromDepartment(p, d);
    }

    //=========== Filtered Department List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Department} backed by the internal list of
     * {@code versionedsudoHr}
     */
    @Override
    public ObservableList<Department> getFilteredDepartmentList() {
        return filteredDepartments;
    }

    @Override
    public void updateFilteredDepartmentList(Predicate<Department> predicate) {
        requireNonNull(predicate);
        filteredDepartments.setPredicate(predicate);
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
        return sudoHr.equals(other.sudoHr)
                && userPrefs.equals(other.userPrefs)
                && filteredEmployees.equals(other.filteredEmployees);
    }

}
