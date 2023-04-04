package taa.model;

import javafx.collections.ObservableList;
import taa.model.student.Student;

/**
 * Unmodifiable view of a student list.
 */
public interface ReadOnlyStudentList {

    /**
     * Returns an unmodifiable view of a student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();


}
