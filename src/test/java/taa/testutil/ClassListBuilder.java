package taa.testutil;

import taa.model.ClassList;
import taa.model.student.Student;

/**
 * A utility class to help with building ClassList objects.
 * Example usage: <br>
 *     {@code ClassList ab = new ClassListBuilder().withPerson("John", "Doe").build();}
 */
public class ClassListBuilder {

    private ClassList classList;

    public ClassListBuilder() {
        classList = new ClassList();
    }

    public ClassListBuilder(ClassList classList) {
        this.classList = classList;
    }

    /**
     * Adds a new {@code Student} to the {@code ClassList} that we are building.
     */
    public ClassListBuilder withPerson(Student student) {
        classList.addStudent(student);
        return this;
    }

    public ClassList build() {
        return classList;
    }
}
