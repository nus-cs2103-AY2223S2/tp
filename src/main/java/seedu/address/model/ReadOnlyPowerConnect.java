package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPowerConnect {
    ObservableList<Student> getStudentList();
    ObservableList<Parent> getParentList();

}
