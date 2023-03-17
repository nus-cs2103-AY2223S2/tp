package seedu.sudohr.model;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.employee.Employee;

/**
 * Unmodifiable view of a SudoHR
 */
public interface ReadOnlySudoHr {

    /**
     * Returns an unmodifiable view of the employee list.
     * This list will not contain any duplicate employees.
     */
    ObservableList<Employee> getEmployeeList();

    /**
     * Returns an unmodifiable view of the departments list.
     * This list will not contain any duplicate departments.
     */
    ObservableList<Department> getDepartmentList();


    /**
     * Returns an unmodifiable view of the leaves list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Leave> getLeavesList();

}
