package seedu.sudohr.model;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.employee.Employee;

/**
 * Unmodifiable view of an sudohr book
 */
public interface ReadOnlySudoHr {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Employee> getPersonList();

    /**
     * Returns an unmodifiable view of the departments list.
     * This list will not contain any duplicate departments.
     */
    ObservableList<Department> getDepartmentList();

}
