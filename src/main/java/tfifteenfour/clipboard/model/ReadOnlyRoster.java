package tfifteenfour.clipboard.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRoster {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    // ObservableList<Student> getUnmodifiableStudentList();

    // ObservableList<Student> getModifiableStudentList();

    ObservableList<Course> getUnmodifiableCourseList();

    FilteredList<Course> getUnmodifiableFilteredCourseList();

}
