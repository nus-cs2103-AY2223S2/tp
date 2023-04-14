package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;
import seedu.sudohr.model.leave.LeaveSortedByDateComparator;

/**
 * Represents the in-memory model of the SudoHR data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SudoHr sudoHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredEmployees;
    private final FilteredList<Department> filteredDepartments;
    private final FilteredList<Leave> filteredLeaves;
    private final FilteredList<Employee> refreshedEmployees;
    private final FilteredList<Department> refreshedDepartments;
    private final FilteredList<Leave> refreshedLeaves;
    private final SortedList<Leave> sortedLeaves;

    /**
     * Initializes a ModelManager with the given sudoHr and userPrefs.
     */
    public ModelManager(ReadOnlySudoHr sudoHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(sudoHr, userPrefs);

        logger.fine("Initializing with SudoHR: " + sudoHr + " and user prefs " + userPrefs);

        this.sudoHr = new SudoHr(sudoHr);
        this.userPrefs = new UserPrefs(userPrefs);

        refreshedEmployees = new FilteredList<>(this.sudoHr.getEmployeeList(), (l) -> true);
        refreshedDepartments = new FilteredList<>(this.sudoHr.getDepartmentList(), (l) -> true);
        refreshedLeaves = new FilteredList<>(this.sudoHr.getLeavesList(), (l) -> true);
        filteredEmployees = new FilteredList<>(refreshedEmployees);
        filteredDepartments = new FilteredList<>(refreshedDepartments);
        filteredLeaves = new FilteredList<>(refreshedLeaves, PREDICATE_SHOW_ALL_NON_EMPTY_LEAVES);
        sortedLeaves = new SortedList<>(this.filteredLeaves, new LeaveSortedByDateComparator());
    }

    public ModelManager() {
        this(new SudoHr(), new UserPrefs());
    }


    // =========== UserPrefs
    // ==================================================================================

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

    @Override
    public void refresh() {
        refreshedEmployees.setPredicate((e) -> false);
        refreshedDepartments.setPredicate((d) -> false);
        refreshedLeaves.setPredicate((l) -> false);
        refreshedEmployees.setPredicate((e)->true);
        refreshedDepartments.setPredicate((d)->true);
        refreshedLeaves.setPredicate((l)->true);
    }

    // =========== SudoHr
    // ================================================================================

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
    public boolean checkEmployeeExists(Id id) {
        requireNonNull(id);
        return sudoHr.checkEmployeeExists(id);
    }

    @Override
    public Employee getEmployee(Id employeeId) {
        requireNonNull(employeeId);
        return sudoHr.getEmployee(employeeId);
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return sudoHr.hasEmployee(employee);
    }

    @Override
    public boolean hasEmployee(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireAllNonNull(excludeFromCheck);
        return sudoHr.hasEmployee(employee, excludeFromCheck);
    }

    @Override
    public boolean hasClashingEmail(Employee employee) {
        requireNonNull(employee);
        return sudoHr.hasClashingEmail(employee);
    }

    @Override
    public boolean hasClashingEmail(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireNonNull(excludeFromCheck);
        return sudoHr.hasClashingEmail(employee, excludeFromCheck);
    }

    @Override
    public boolean hasClashingPhoneNumber(Employee employee) {
        requireNonNull(employee);
        return sudoHr.hasClashingPhoneNumber(employee);
    }

    @Override
    public boolean hasClashingPhoneNumber(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireNonNull(excludeFromCheck);
        return sudoHr.hasClashingPhoneNumber(employee, excludeFromCheck);
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

    // =========== Leave Commands
    // =============================================================

    @Override
    public void addLeave(Leave leave) {
        requireNonNull(leave);
        sudoHr.addLeave(leave);

    }

    @Override
    public Leave getLeave(LeaveDate date) {
        return sudoHr.getLeave(date);
    }

    @Override
    public boolean hasLeave(Leave leave) {
        requireNonNull(leave);
        return sudoHr.hasLeave(leave);
    }

    @Override
    public ObservableList<Leave> getLeavesList() {
        return this.sudoHr.getLeavesList();
    }

    @Override
    public Leave getInternalLeaveIfExist(Leave leaveToAdd) {
        if (sudoHr.hasLeave(leaveToAdd)) {
            return sudoHr.getInternalLeaveIfExist(leaveToAdd);
        } else {
            sudoHr.addLeave(leaveToAdd);
            return leaveToAdd;
        }
    }

    @Override
    public boolean hasEmployeeOnLeave(LeaveDate date, Employee employee) {
        requireAllNonNull(date, employee);
        return sudoHr.hasEmployeeOnLeave(date, employee);
    }

    @Override
    public void addEmployeeToLeave(Leave leaveToAdd, Employee employeeToAdd) {
        requireAllNonNull(leaveToAdd, employeeToAdd);

        sudoHr.addEmployeeToLeave(leaveToAdd, employeeToAdd);
    }

    @Override
    public void deleteEmployeeFromLeave(Leave leaveToDelete, Employee employeeToDelete) {
        requireAllNonNull(leaveToDelete, employeeToDelete);
        sudoHr.deleteEmployeeFromLeave(leaveToDelete, employeeToDelete);
    }

    @Override
    public int getCountOnLeave(Leave leave) {
        requireNonNull(leave);
        return sudoHr.getCountOnLeave(leave);
    }

    @Override
    public int getCountOnLeave(LeaveDate date) {
        requireNonNull(date);
        Leave leave = getLeave(date);
        return getCountOnLeave(leave);
    }

    @Override
    public void cascadeUpdateUserInLeaves(Employee employeeToEdit, Employee editedEmployee) {
        requireAllNonNull(employeeToEdit, editedEmployee);
        sudoHr.cascadeUpdateUserInLeaves(employeeToEdit, editedEmployee);
    }

    @Override
    public void cascadeDeleteUserInLeaves(Employee employeeToDelete) {
        requireAllNonNull(employeeToDelete);
        sudoHr.cascadeDeleteUserInLeaves(employeeToDelete);
    }

    // =========== Filtered Leave List Accessors
    // =============================================================

    @Override
    public ObservableList<Leave> getFilteredLeaveList() {
        return sortedLeaves;
    }

    @Override
    public void updateFilteredLeaveList(Predicate<Leave> predicate) {
        requireNonNull(predicate);
        filteredLeaves.setPredicate(predicate);
    }

    // =========== Filtered Employee List Accessors
    // =============================================================

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
    public int getCountForDepartment(Department department) {
        requireNonNull(department);
        return sudoHr.getCountForDepartment(department);
    }

    @Override
    public int getCountForDepartment(DepartmentName departmentName) {
        requireNonNull(departmentName);
        Department dept = getDepartment(departmentName);
        return getCountForDepartment(dept);
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

    @Override
    public void cascadeDeleteEmployeeToDepartments(Employee employeeToDelete) {
        requireNonNull(employeeToDelete);
        sudoHr.cascadeDeleteEmployeeToDepartments(employeeToDelete);
    }

    @Override
    public void cascadeEditEmployeeToDepartments(Employee employeeToEdit, Employee editedEmployee) {
        requireAllNonNull(employeeToEdit, editedEmployee);
        sudoHr.cascadeEditEmployeeToDepartments(employeeToEdit, editedEmployee);
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
                && filteredEmployees.equals(other.filteredEmployees)
                && filteredDepartments.equals(other.filteredDepartments)
                && filteredLeaves.equals(other.filteredLeaves);
    }
}
