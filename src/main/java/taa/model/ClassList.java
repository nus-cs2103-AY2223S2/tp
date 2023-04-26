package taa.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import taa.model.student.Student;
import taa.model.student.UniqueStudentList;

/**
 * Adds another layer on top of the students in the application, allow them to be organized in classes Duplicates are
 * not allowed (by .isSamePerson comparison)
 */
//Credits: Solution below are adapted from original AB3 codes, with significant modifications.
public class ClassList implements ReadOnlyStudentList {
    public static final String STR_SEP = ";";
    private final UniqueStudentList students;
    private final String className;
    private int studentCount;

    /**
     * Creates a class list instance with the given class name.
     *
     * @param name the name of the class.
     */
    public ClassList(String name) {
        students = new UniqueStudentList();
        this.className = name;
    }

    /**
     * Creates a class list instance with a default name.
     */
    public ClassList() {
        this("DEFAULT");
    }

    /**
     * Creates an ClassList using the Persons in the {@code toBeCopied}
     */
    public ClassList(ReadOnlyStudentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /** @return true iff both persons have the same name. */
    public boolean isSameClassList(ClassList otherClassList) {
        return otherClassList == this || (otherClassList != null
                && Objects.equals(otherClassList.getClassName(), getClassName()));
    }

    public String getClassName() {
        return this.className;
    }


    /**
     * Replaces the contents of the student list with {@code students}. {@code students} must not contain duplicate
     * students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code ClassList} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentList newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the class list.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book. The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
        this.studentCount += 1;
    }

    /**
     * Updates the student list to propagate change to the rest of the model.
     *
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
     * Replaces the given student {@code target} in the list with {@code editedStudent}. {@code target} must exist in
     * the address book. The student identity of {@code editedStudent} must not be the same as another existing student
     * in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code ClassList}. {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
        this.studentCount -= 1;
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    //Credits: Solution below is adapted from ChatGPT3.5 codes.
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassList // instanceof handles nulls
                && students.equals(((ClassList) other).students));
    }

    @Override
    public String toString() {
        return "class name: " + this.className + " " + students.asUnmodifiableObservableList().size() + " person(s)";

    }
}
