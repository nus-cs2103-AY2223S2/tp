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
 * Represents a list of classes under the same tutor.
 */
public class UniqueClassLists implements Iterable<ClassList> {

    private final ObservableList<ClassList> internalList = FXCollections.observableArrayList();
    private final ObservableList<ClassList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public UniqueClassLists(ClassList classList) {
        this.internalList.add(classList);
    }

    public UniqueClassLists() {
    }
    /**
     * @return true if the list contains an equivalent student as the given argument.
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
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    /**
     * Adds a student to a class.
     * @param student The student to be added.
     * @param className The name of the class that the student joins.
     */
    public void addStudent(Student student, String className) {
        for (ClassList lst: this.internalList) {
            if (lst.getClassName().equals(className) && !lst.hasStudent(student)) {
                lst.addStudent(student);
                return;
            }
        }

        // If we are here, then no such class with the specified className exists.
        // We shall then proceed to create a new Class List to store this student.
        ClassList newClassList = new ClassList(className);
        newClassList.addStudent(student);
        this.internalList.add(newClassList);
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
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
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
     * @return true iff {@code students} contains only unique students.
     */
    private boolean classListsAreUnqiue(List<ClassList> classLists) {
        final int size = classLists.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (classLists.get(i).isSameClassList(classLists.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return the backing list as an unmodifiable {@code ObservableList}.
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

}
