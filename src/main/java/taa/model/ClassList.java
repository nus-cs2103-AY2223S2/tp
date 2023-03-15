package taa.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import taa.assignment.AssignmentList;
import taa.model.student.Student;
import taa.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ClassList implements ReadOnlyAddressBook {

    private static int lastId = 0;

    private UniqueStudentList students;
    private AssignmentList assignments;
    private String className = "DEFAULT";
    private int classId;
    private int studentCount = 0;

    /**
     * Creates a class list instance with the given class name.
     * @param name the name of the class.
     */
    public ClassList(String name) {
        assignments = new AssignmentList();
        students = new UniqueStudentList();
        this.classId = ++lastId;
        this.className = name;
    }

    /**
     * Creates a class list instance with a default name.
     */
    public ClassList() {
        assignments = new AssignmentList();
        students = new UniqueStudentList();
        this.classId = ++lastId;
    }

    /**
     * Creates an ClassList using the Persons in the {@code toBeCopied}
     */
    public ClassList(ReadOnlyAddressBook toBeCopied) {
        this("DEFAULT");
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameClassList(ClassList otherClassList) {
        if (otherClassList == this) {
            return true;
        }

        return otherClassList != null
                && otherClassList.getClassId() == (getClassId());
    }

    public String getClassId() {
        return this.className;
    }


    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code ClassList} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
        this.studentCount += 1;
    }

    /**
     * Updates the student list to propagate change to the rest of the model.
     * @param target The student to be refreshed.
     */
    public void updateStudent(Student target) {
        students.update(target);
    }

    public int getStudentCount() {
        return this.studentCount;
    }

    public UniqueStudentList getUniqueStudentList() {
        return this.students;
    }
    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code ClassList}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
        this.studentCount -= 1;
    }

    //// util methods

    @Override
    public String toString() {
        return "class name: " + this.className + " " + students.asUnmodifiableObservableList().size() + " persons";

    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassList // instanceof handles nulls
                && students.equals(((ClassList) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
