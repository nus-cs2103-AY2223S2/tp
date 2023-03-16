package seedu.address.model.person;

import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;

public interface ReadOnlyPCClass {
    Class getPcClass();
    ObservableList<Student> getStudentList();
}
