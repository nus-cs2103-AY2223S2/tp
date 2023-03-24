package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.exceptions.CourseNotFoundException;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateGroupException;


public class UniqueCoursesList implements Iterable<Course> {

    private final ObservableList<Course> internalList = FXCollections.observableArrayList();
    private final ObservableList<Course> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Course toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCourse);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Course toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedCourse}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedCourse} must not be the same as another existing student in the list.
     */
    public void setCourse(Course target, Course editedCourse) {
        requireAllNonNull(target, editedCourse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CourseNotFoundException();
        }

        if (!target.isSameCourse(editedCourse) && contains(editedCourse)) {
            throw new DuplicateGroupException();
        }

        internalList.set(index, editedCourse);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Course toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CourseNotFoundException();
        }
    }

    public void setCourses(UniqueCoursesList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code courses}.
     * {@code courses} must not contain duplicate courses.
     */
    public void setCourses(List<Course> courses) {
        requireAllNonNull(courses);
        if (!coursesAreUnique(courses)) {
            throw new DuplicateGroupException();
        }

        internalList.setAll(courses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Course> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as a modifiable {@code ObservableList}.
     */
    public ObservableList<Course> asModifiableObservableList() {
        return internalList;
    }

    @Override
    public Iterator<Course> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCoursesList // instanceof handles nulls
                        && internalList.equals(((UniqueCoursesList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code courses} contains only unique courses.
     */
    private boolean coursesAreUnique(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(i).isSameCourse(courses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
