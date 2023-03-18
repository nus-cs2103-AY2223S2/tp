package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateClassException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;


/**
 * Represents a Class in the address book.
 */
public class Class {

    public static final String MESSAGE_CONSTRAINTS =
            "Class name must contain letters and/or digits with no spaces in between";
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static List<Class> classes = new ArrayList<>();

    // TODO: Change type to Name
    private final String className;
    private UniqueStudentList students;
    private UniqueParentList parents;

    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     * @param students A list of students in the class.
     * @param parents A list of parents in the class.
     */
    private Class(String className,
                  UniqueStudentList students,
                  UniqueParentList parents) throws DuplicateClassException {
        requireNonNull(className);
        checkArgument(isValidClass(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        this.students = students;
        this.parents = parents;
        if (classes.contains(this)) {
            classes.remove(this);
            classes.add(this);
            throw new DuplicateClassException();
        }
        classes.add(this);
    }

    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     */
    private Class(String className) throws DuplicateClassException {
        requireNonNull(className);
        checkArgument(isValidClass(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        this.students = new UniqueStudentList();
        this.parents = new UniqueParentList();
        if (classes.contains(this)) {
            throw new DuplicateClassException();
        }
        classes.add(this);
    }

    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     * @return The class with the given name.
     */
    public static Class of(String className) {
        try {
            return new Class(className);
        } catch (DuplicateClassException e) {
            return getClass(className);
        }
    }
    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     * @param students A list of students in the class.
     * @param parents A list of parents in the class.
     * @return The class with the given name.
     */
    public static Class of(String className, UniqueStudentList students, UniqueParentList parents) {
        try {
            return new Class(className, students, parents);
        } catch (DuplicateClassException e) {
            return getClass(className);
        }
    }

    /**
     * Constructs a {@code Class}.
     * @param c The class to be added to static list.
     * @return The class with the given name.
     */
    public static Class of(Class c) {
        try {
            return new Class(c.getClassName(), c.getStudents(), c.getParents());
        } catch (DuplicateClassException e) {
            return getClass(c.getClassName());
        }
    }

    /**
     * Updates the list of classes.
     * @param classes The new list of classes.
     */
    public static void setClasses(List<Class> classes) {
        Class.classes = classes;
    }

    /**
     * Returns true if a given string is a valid class name.
     *
     * @param className Test string to be checked.
     */
    public static boolean isValidClass(String className) {
        return className.matches(VALIDATION_REGEX);
    }

    /**
     * Get the class with the given name.
     *
     * @param className The name of the class to be retrieved.
     * @return The class with the given name.
     */
    public static Class getClass(String className) {
        for (Class c : classes) {
            if (c.getClassName().equals(className)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the list of all classes.
     *
     * @return The list of all classes.
     */
    public static List<Class> getClasses() {
        return classes;
    }
    /**
     * Get the list of all people in all classes
     *
     * @return The list of all people in all classes
     */
    public static ObservableList<Person> getPersons() {
        ObservableList<Person> persons = new UniquePersonList().asUnmodifiableObservableList();
        for (Class c : classes) {
            persons.addAll(c.getStudents().asUnmodifiableObservableList());
            persons.addAll(c.getParents().asUnmodifiableObservableList());
        }
        return persons;
    }

    /**
     * Get the list of all students in all classes.
     *
     * @return The list of all students in all classes.
     */
    public static UniqueStudentList getAllStudents() {
        UniqueStudentList allStudents = new UniqueStudentList();
        for (Class c : classes) {
            allStudents.setStudents(c.getStudents());
        }
        return allStudents;
    }

    /**
     * Get the list of all parents in all classes.
     *
     * @return The list of all parents in all classes.
     */
    public static UniqueParentList getAllParents() {
        UniqueParentList allParents = new UniqueParentList();
        for (Class c : classes) {
            allParents.setParents(c.getParents());
        }
        return allParents;
    }

    /**
     * Remove the class from the list of classes.
     * @param c The class to be removed.
     */
    public static void removeClass(Class c) {
        classes.remove(c);
    }

    /**
     * Get the name of the class.
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Get the list of students in the class.
     *
     * @return The list of students in the class.
     */
    public UniqueStudentList getStudents() {
        return students;
    }

    /**
     * Get the list of parents in the class.
     *
     * @return The list of parents in the class.
     */
    public UniqueParentList getParents() {
        return parents;
    }

    /**
     * Check if the class has a student.
     *
     * @param student The student to be checked.
     * @return True if the class has the student.
     */
    public boolean hasStudent(Student student) {
        return students.contains(student);
    }

    /**
     * Check if the class has a parent.
     *
     * @param parent The parent to be checked.
     * @return True if the class has the parent.
     */
    public boolean hasParent(Parent parent) {
        return parents.contains(parent);
    }

    /**
     * Add a student to the class.
     * @param student The student to be added.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Add a parent to the class.
     *
     * @param parent The parent to be added.
     */
    public void addParent(Parent parent) {
        parents.add(parent);
    }

    /**
     * Remove a student from the class.
     *
     * @param student The student to be removed.
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Remove a parent from the class.
     *
     * @param parent The parent to be removed.
     */
    public void removeParent(Parent parent) {
        parents.remove(parent);
    }

    /**
     * Replaces students in the class with another list.
     *
     * @param students The UniqueStudentList of students to be replaced with.
     */
    public void setStudents(UniqueStudentList students) {
        this.students = students;
    }

    /**
     * Overloaded method if user inputs List instead of UniqueStudentList.
     *
     * @param students The List of students to be replaced with.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Update a student in the class.
     * @param target The student to be updated.
     * @param editedStudent The student to be updated to.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Replaces parents in the class with another list.
     *
     * @param parents The UniqueParentList of parents to be replaced with.
     */
    public void setParents(UniqueParentList parents) {
        this.parents = parents;
    }

    /**
     * Overloaded method if user inputs List instead of UniqueParentList.
     *
     * @param parents The List of parents to be replaced with.
     */
    public void setParents(List<Parent> parents) {
        this.parents.setParents(parents);
    }

    /**
     * Update a student in the class.
     *
     * @param target The student to be updated.
     * @param editedStudent The student to be updated to.
     */
    public void updateStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Update a parent in the class.
     *
     * @param target The parent to be updated.
     * @param editedParent The parent to be updated to.
     */
    public void updateParent(Parent target, Parent editedParent) {
        requireNonNull(editedParent);
        parents.setParent(target, editedParent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Class aClass = (Class) o;
        return className.equals(aClass.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }

    /**
     * A method to check if both are of same class
     * @param aClass
     * @return true if same class, false otherwise
     */
    public boolean isSameClass(Class aClass) {
        if (aClass == this) {
            return true;
        }
        return aClass != null && aClass.getClassName().equals(getClassName());
    }
}
