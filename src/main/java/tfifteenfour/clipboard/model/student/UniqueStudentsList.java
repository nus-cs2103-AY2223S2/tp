package tfifteenfour.clipboard.model.student;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import tfifteenfour.clipboard.model.UniqueList;
import tfifteenfour.clipboard.model.student.exceptions.DuplicateStudentException;
import tfifteenfour.clipboard.model.student.exceptions.StudentNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}. As such,
 * adding and updating of persons uses Student#isSameStudent(Student) for equality so as to ensure that
 * the student being added or updated is unique in terms of identity in the UniqueStudentList. However,
 * the removal of a student uses Student#equals(Object) so as to ensure that the student with exactly
 * the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentsList extends UniqueList<Student> {

    @Override
    public UniqueStudentsList copy() {
        UniqueStudentsList copy = new UniqueStudentsList();
        this.internalList.forEach(student -> copy.add(student.copy()));

        return copy;
    }

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    @Override
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    @Override
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    @Override
    public void set(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudent(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentsList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentsList) other).internalList));
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    @Override
    protected boolean elementsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
