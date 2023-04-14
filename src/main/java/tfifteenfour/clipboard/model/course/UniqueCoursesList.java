package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import tfifteenfour.clipboard.model.UniqueList;
import tfifteenfour.clipboard.model.course.exceptions.CourseNotFoundException;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateGroupException;

/**
 * A list of courses that enforces uniqueness between its elements and does not allow nulls.
 * A course is considered unique by comparing using {@code Course#isSameCourse(Course)}. As such,
 * adding and updating of courses uses Course#isSameCourse(Course) for equality so as to ensure that
 * the course being added or updated is unique in terms of identity in the UniqueCoursesList. However,
 * the removal of a course uses Course#equals(Object) so as to ensure that the course with exactly
 * the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Course#isSameCourse(Course)
 */
public class UniqueCoursesList extends UniqueList<Course> {

    @Override
    public UniqueCoursesList copy() {
        UniqueCoursesList copy = new UniqueCoursesList();
        this.internalList.forEach(course -> copy.add(course.copy()));


        return copy;
    }

    /**
     * Returns true if the list contains an equivalent course as the given argument.
     */
    @Override
    public boolean contains(Course toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCourse);
    }

    /**
     * Adds a course to the list.
     * The course must not already exist in the list.
     */
    @Override
    public void add(Course toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the course {@code target} in the list with {@code editedCourse}.
     * {@code target} must exist in the list.
     * The course identity of {@code editedCourse} must not be the same as another existing course in the list.
     */
    @Override
    public void set(Course target, Course editedCourse) {
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
    protected boolean elementsAreUnique(List<Course> courses) {
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
