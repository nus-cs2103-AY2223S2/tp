package tfifteenfour.clipboard.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.UniqueCoursesList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Roster implements ReadOnlyRoster {

    private final UniqueCoursesList courses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        courses = new UniqueCoursesList();
    }

    public Roster() {}

    /**
     * Creates an Roster using the Students in the {@code toBeCopied}
     */
    public Roster(ReadOnlyRoster toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setCourses(List<Course> courses) {
        this.courses.setInternalList(courses);
    }

    public void updateFilteredCourses(Predicate<Course> predicate) {
        this.courses.updateFilterPredicate(predicate);
    }

    /**
     * Returns true if a course with the same identity as {@code course} exists in the address book.
     */
    public boolean hasCourse(Course course) {
        requireNonNull(course);
        return courses.contains(course);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }

    public void setCourse(Course courseToReplace, Course newCourse) {
        courses.set(courseToReplace, newCourse);
    }

    /**
     * Resets the existing data of this {@code Roster} with {@code newData}.
     */
    public void resetData(ReadOnlyRoster newData) {
        requireNonNull(newData);
        // setStudents(newData.getUnmodifiableStudentList());
        setCourses(newData.getUnmodifiableCourseList());
    }

    public ObservableList<Course> getModifiableCourseList() {
        return courses.asModifiableObservableList();
    }

    @Override
    public ObservableList<Course> getUnmodifiableCourseList() {
        return courses.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Course> getUnmodifiableFilteredCourseList() {
        return courses.asUnmodifiableFilteredList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Roster // instanceof handles nulls
                && courses.equals(((Roster) other).courses));
    }

    @Override
    public int hashCode() {
        return courses.hashCode();
    }


    /**
     * Copies this roster and its current state
     * @return a copy of this roster
     */
    public Roster copy() {
        Roster copy = new Roster();
        copy.setCourses(courses.copy().asUnmodifiableObservableList());
        return copy;
    }
}
