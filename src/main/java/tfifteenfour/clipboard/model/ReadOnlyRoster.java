package tfifteenfour.clipboard.model;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRoster {

    ObservableList<Course> getUnmodifiableCourseList();

    ObservableList<Course> getUnmodifiableFilteredCourseList();

}
