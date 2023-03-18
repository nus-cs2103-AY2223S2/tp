package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.department.UniqueDepartmentList;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.UniqueEmployeeList;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.UniqueLeaveList;


/**
 * Wraps all data at the SudoHR-level
 * Duplicates are not allowed (by :isSameEmployee comparison)
 */
public class SudoHr implements ReadOnlySudoHr {

    private final UniqueEmployeeList employees;
    private final UniqueDepartmentList departments;
    private final UniqueLeaveList leaves;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        leaves = new UniqueLeaveList();
        employees = new UniqueEmployeeList();
        departments = new UniqueDepartmentList();
    }

    public SudoHr() {
    }

    /**
     * Creates a SudoHR using the Employees in the {@code toBeCopied}
     */
    public SudoHr(ReadOnlySudoHr toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Replaces the contents of the department list with {@code departments}.
     * {@code departments} must not contain duplicate departments.
     */
    public void setDepartments(List<Department> departments) {
        this.departments.setDepartments(departments);
    }

    /**
     * Replaces the contents of the leaves list with {@code leaves}.
     * {@code leaves} must not contain duplicate leaves.
     */
    public void setLeaves(List<Leave> leaves) {
        this.leaves.setLeaves(leaves);
    }

    /**
     * Resets the existing data of this {@code SudoHr} with {@code newData}.
     */
    public void resetData(ReadOnlySudoHr newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
        setDepartments(newData.getDepartmentList());
        setLeaves(newData.getLeavesList());
    }

    // =========== Employee-Level Operations ============================

    /**
     * Returns the employee with the given Id.
     * @param id The Id to find.
     * @return The corresponding employee.
     */
    public Employee getEmployee(Id id) {
        return employees.get(id);
    }

    /**
     * Returns true if an employee with the same identity as {@code employee} exists
     * in SudoHR.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Returns true if a employee with the same identity as {@code employee} exists
     * in SudoHR,
     * excluding the specified employee
     */
    public boolean hasEmployee(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireNonNull(excludeFromCheck);
        return employees.contains(employee, excludeFromCheck);
    }

    /**
     * Returns true if an employee shares the same email with a different
     * {@code employee} (different id).
     */
    public boolean hasClashingEmail(Employee employee) {
        requireNonNull(employee);
        return employees.sharesEmail(employee);
    }

    /**
     * Returns true if an employee shares the same email with a different
     * {@code employee} (different id),
     * excluding the specified employee
     */
    public boolean hasClashingEmail(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireNonNull(excludeFromCheck);
        return employees.sharesEmail(employee, excludeFromCheck);
    }

    /**
     * Returns true if an employee shares the same phone number with a different
     * {@code employee} (different id).
     */
    public boolean hasClashingPhoneNumber(Employee employee) {
        requireNonNull(employee);
        return employees.sharesPhoneNumber(employee);
    }

    /**
     * Returns true if a employee shares the same phone number with a different
     * {@code employee} (different id),
     * excluding the specified employee
     */
    public boolean hasClashingPhoneNumber(Employee employee, Employee excludeFromCheck) {
        requireNonNull(employee);
        requireNonNull(excludeFromCheck);
        return employees.sharesPhoneNumber(employee, excludeFromCheck);
    }

    /**
     * Adds an employee to SudoHR.
     * The employee must not already exist in SudoHR and
     * should not have any clashes with email or phone number fields
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Replaces the given employee {@code target} in the list with
     * {@code editedEmployee}.
     * {@code target} must exist in SudoHR.
     * The employee identity of {@code editedEmployee} must not be the same as
     * another existing employee in SudoHR.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code SudoHr}.
     * {@code key} must exist in SudoHR.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
    }

    // =========== Department-Level Operations
    // ==========================================================================

    /**
=======
>>>>>>> 274c3245992792161eb4cc7606b0227c32e31c97
     * Returns the department with the given name.
     *
     * @param name The department name to find.
     * @return The corresponding department.
     */
    public Department getDepartment(DepartmentName name) {
        return departments.getDepartment(name);
    }

    /**
     * Returns true if a department with the same identity as {@code department} exists in SudoHR.
     */
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return departments.contains(department);
    }

    /**
     * Adds a department to SudoHR.
     * The department must not already exist in SudoHR.
     */
    public void addDepartment(Department d) {
        departments.add(d);
    }

    /**
     * Replaces the given department {@code target} in the list with
     * {@code editedEmployee}.
     * {@code target} must exist in SudoHR.
     * The employee identity of {@code editedEmployee} must not be the same as
     * another existing employee in SudoHR.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireNonNull(editedDepartment);
        departments.setDepartment(target, editedDepartment);
    }

    /**
     * Removes {@code key} from this {@code SudoHr}.
     * {@code key} must exist in SudoHR.
     */
    public void removeDepartment(Department key) {
        departments.remove(key);
    }

    /**
     * Adds a given employee from a given department
     *
     * @param p The employee to add
     * @param d The department to add the employee to
     */
    public void addEmployeeToDepartment(Employee p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.addEmployee(p);
    }

    /**
     * Removes a given employee from a given department
     *
     * @param p The employee to remove
     * @param d The department to remove the employee fro
     */
    public void removeEmployeeFromDepartment(Employee p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.removeEmployee(p);
    }

    //// leave-level operations

    /**
     * Adds a leave to the address book.
     * The leave must not already exist in the address book.
     */
    public void addLeave(Leave leave) {
        leaves.addLeave(leave);
    }

    /**
     * Removes {@code leave} from this {@code SudoHr}.
     * {@code leave} must exist in the sudohr book.
     */
    public void deleteLeave(Leave leave) {
        leaves.remove(leave);
    }

    /**
     * Returns true if a leave with the same identity as {@code leave} exists in
     * the sudohr book.
     */
    public boolean hasLeave(Leave leave) {
        requireNonNull(leave);
        return leaves.contains(leave);
    }

    public Leave getLeave(Leave leaveToAdd) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.isSameLeave(leaveToAdd)) {
                return leave;
            }
        }
        return leaveToAdd;
    }

    /**
     * Returns true if a employee with the same identity as {@code employee} exists in
     * the specified leave
     * {@code date}
     * the sudohr book.
     */
    public boolean hasEmployeeOnLeave(Date date, Employee employee) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.getDate().equals(date) & leave.hasEmployee(employee)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a employee {@code employee} to a specified leave{@code leave}
     * in the sudohr book.
     */
    public void addEmployeeToLeave(Leave leave, Employee employee) {
        requireAllNonNull(leave, employee);
        leave.addEmployee(employee);
    }

    /**
     * Deletes a employee {@code employee} from a specified leaeve{@code leave}
     * in the sudohr book.
     */
    public void deleteEmployeeFromLeave(Leave leave, Employee employee) {
        requireAllNonNull(leave, employee);
        leave.deleteEmployee(employee);
    }

    /**
     * Update a employee {@code employee} with editedEmployee {@code employee} in all leaves
     * in the sudohr book.
     */
    public void cascadeUpdateUserInLeaves(Employee employeeToEdit, Employee editedEmployee) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.hasEmployee(employeeToEdit)) {
                leave.deleteEmployee(employeeToEdit);
                leave.addEmployee(editedEmployee);
            }
        }
    }

    /**
     * Deletes a employee {@code employee} from all leaves
     * in the sudohr book.
     */
    public void cascadeDeleteUserInLeaves(Employee employeeToDelete) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.hasEmployee(employeeToDelete)) {
                leave.deleteEmployee(employeeToDelete);
            }
        }
    }

    /**
     * return the list of all leaves in sudohr
     */
    @Override
    public ObservableList<Leave> getLeavesList() {
        return leaves.asUnmodifiableObservableList();
    }

    //// leave-level operations

    //// util methods

    @Override
    public String toString() {
        return employees.asUnmodifiableObservableList().size() + " employees";
        // TODO: refine later
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Department> getDepartmentList() {
        return departments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SudoHr // instanceof handles nulls
                        && employees.equals(((SudoHr) other).employees));
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
