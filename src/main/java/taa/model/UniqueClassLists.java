package taa.model;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import taa.commons.util.CollectionUtil;
import taa.model.student.Student;
import taa.model.student.exceptions.DuplicateStudentException;
import taa.model.student.exceptions.StudentNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSamePerson(Student)}.
 * As such, adding and updating of persons uses Student#isSamePerson(Student) for equality
 * so as to ensure that the student being added or updated is unique in terms of identity
 * in the UniqueStudentList. However, the removal of a student uses Student#equals(Object) so
 * as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueClassLists implements Iterable<ClassList> {

    private final ObservableList<ClassList> internalList = FXCollections.observableArrayList();
    private final ObservableList<ClassList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(ClassList toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClassList);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(ClassList toAdd) {
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
    public void setClassLists(ClassList target, ClassList editedClassList) {
        CollectionUtil.requireAllNonNull(target, editedClassList);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameClassList(editedClassList) && contains(editedClassList)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedClassList);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setClassLists(taa.model.UniqueClassLists replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setClassLists(List<ClassList> classLists) {
        CollectionUtil.requireAllNonNull(classLists);
        if (!classListsAreUnqiue(classLists)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(classLists);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ClassList> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ClassList> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof taa.model.UniqueClassLists // instanceof handles nulls
                && internalList.equals(((taa.model.UniqueClassLists) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean classListsAreUnqiue(List<ClassList> classLists) {
        for (int i = 0; i < classLists.size() - 1; i++) {
            for (int j = i + 1; j < classLists.size(); j++) {
                if (classLists.get(i).isSameClassList(classLists.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

