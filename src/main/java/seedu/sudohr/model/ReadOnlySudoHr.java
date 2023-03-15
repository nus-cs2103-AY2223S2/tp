package seedu.sudohr.model;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.person.Person;

/**
 * Unmodifiable view of an sudohr book
 */
public interface ReadOnlySudoHr {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();


    /**
     * Returns an unmodifiable view of the leaves list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Leave> getLeavesList();

    /**
     * Returns an unmodifiable view of the departments list.
     * This list will not contain any duplicate departments.
     */
    ObservableList<Department> getDepartmentList();

}
