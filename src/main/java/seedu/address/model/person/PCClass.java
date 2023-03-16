package seedu.address.model.person;

import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;
import seedu.address.model.person.Class;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class PCClass implements ReadOnlyPCClass {
    private List<Class> classes = Class.getClasses();

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
    public void setPcClass(List<Class> pcClasses) {
        this.classes = pcClasses;
        Class.setClasses(pcClasses);
    }

    /**
     * Resets the existing data of this {@code PCClass} with {@code newData}.
     */
    public void resetData(ReadOnlyPCClass newData) {
        requireNonNull(newData);
        setPcClass(newData.getClassList());
    }

    //// class-level operations

    /**
     * Returns true if a class with the same identity as {@code class} exists in the class.
     */
    public boolean hasClass(Class pcClass) {
        requireNonNull(pcClass);
        for (Class c : classes) {
            if (c.equals(pcClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the class.
     */
    public void addClass(Class p) {
        Class.of(p);
    }

    /**
     * Replaces the given class {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the class.
     * The class identity of {@code editedStudent} must not be the same as another existing class in
     * the class.
     */
    public void setClass(Class target, Class editedClass) {
        requireNonNull(editedClass);
        classes.remove(target);
        addClass(editedClass);
    }

    /**
     * Removes {@code key} from this {@code PCClass}.
     * {@code key} must exist in the class.
     */
    public void removeClass(Class key) {
        Class.removeClass(key);
        this.classes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return classes.size() + " students";
    }

    public List<Class> getClassList() {
        return classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PCClass pcClass = (PCClass) o;
        return Objects.equals(classes, pcClass.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classes);
    }
}
