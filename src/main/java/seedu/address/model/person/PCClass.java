package seedu.address.model.person;

import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PCClass implements ReadOnlyPCClass {
    private Class pcClass;
    private final UniqueStudentList students;

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

    public PCClass() {}

    /**
     * Creates a PCClass using the Students in the {@code toBeCopied}
     */
    public PCClass(ReadOnlyPCClass toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setPcClass(Class pcClass) {
        this.pcClass = pcClass;
    }
    
    public void setStudents() {
        this.students.setStudents(pcClass.getStudents());
    }
    
    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code PCClass} with {@code newData}.
     */
    public void resetData(ReadOnlyPCClass newData) {
        requireNonNull(newData);
        setPcClass(newData.getPcClass());
        setStudents();
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the class.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return pcClass.hasStudent(student);
    }

    /**
     * Adds a student to the class.
     * The student must not already exist in the class.
     */
    public void addStudent(Student p) {
        this.pcClass.addStudent(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the class.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the class.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        pcClass.updateStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code PCClass}.
     * {@code key} must exist in the class.
     */
    public void removeStudent(Student key) {
        pcClass.removeStudent(key);
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
    public Class getPcClass() {
        return pcClass;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PCClass // instanceof handles nulls
                && students.equals(((PCClass) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
