package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;

/**
 * Represents a Course in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCourseCode(String)} (String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course codes can only contain alphanumeric and special characters";
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}\\p{Punct}]+$";

    public final String courseCode;
    private final UniqueGroupsList groups;

    {
        groups = new UniqueGroupsList();
    }

    /**
     * Constructs a {@code courseCode}.
     * @param courseCode A valid module code.
     */
    public Course(String courseCode) {
        requireNonNull(courseCode);
        this.courseCode = courseCode;
    }

    /**
     * Creates a copy of this instance. Used for saving states for undo command.
     * @return a copy of this instance.
     */
    public Course copy() {
        Course copy = new Course(this.courseCode);
        UniqueGroupsList groupsCopy = new UniqueGroupsList();
        groups.asUnmodifiableObservableList().forEach(group -> groupsCopy.add(group.copy()));
        copy.setGroups(groupsCopy);

        return copy;

    }

    public void setGroups(UniqueGroupsList groups) {
        this.groups.setInternalList(groups.asUnmodifiableObservableList());
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidCourseCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public ObservableList<Group> getUnmodifiableGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    public ObservableList<Group> getModifiableGroupList() {
        return groups.asModifiableObservableList();
    }

    public ObservableList<Group> getUnmodifiableFilteredGroupList() {
        return groups.asUnmodifiableFilteredList();
    }

    public void updateFilteredGroups(Predicate<Group> predicate) {
        this.groups.updateFilterPredicate(predicate);
    }


    /**
     * Returns course code
     * @return string course code
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && courseCode.equals(((Course) other).courseCode)); // state check
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        return courseCode;
    }

    /**
     * Checks if course has specified group.
     * @return true if group has specified group.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds the given group to this course.
     * @param group Group to be added.
     */
    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Deletes the given group from this course.
     * @param group Group to be deleted.
     */
    public void deleteGroup(Group group) {
        groups.remove(group);
    }

    public void setGroup(Group groupToReplace, Group newGroup) {
        groups.set(groupToReplace, newGroup);
    }


    /**
     * Returns true if both courses are the same.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseCode().equals(getCourseCode());
    }
}
