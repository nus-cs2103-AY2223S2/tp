package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the mathutoring level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Mathutoring implements ReadOnlyMathutoring {

    private final UniqueStudentList students;

    private Student selectedStudent;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public Mathutoring() {}

    /**
     * Creates a Mathutoring using the Students in the {@code toBeCopied}
     */
    public Mathutoring(ReadOnlyMathutoring toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code Mathutoring} with {@code newData}.
     */
    public void resetData(ReadOnlyMathutoring newData) {
        requireNonNull(newData);
        this.selectedStudent = null;
        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the matutoring.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the mathutoring.
     * The student must not already exist in the mathutoring.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the mathutoring.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the mathutoring.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Mathutoring}.
     * {@code key} must exist in the mathutoring.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    public void checkStudent(Student key) {
        selectedStudent = key;
    }

    public Student findCheckedStudent() {
        return selectedStudent;
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mathutoring // instanceof handles nulls
                && students.equals(((Mathutoring) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
