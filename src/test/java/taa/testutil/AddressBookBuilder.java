package taa.testutil;

import taa.model.ClassList;
import taa.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ClassList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ClassList classList;

    public AddressBookBuilder() {
        classList = new ClassList();
    }

    public AddressBookBuilder(ClassList classList) {
        this.classList = classList;
    }

    /**
     * Adds a new {@code Student} to the {@code ClassList} that we are building.
     */
    public AddressBookBuilder withPerson(Student student) {
        classList.addStudent(student);
        return this;
    }

    public ClassList build() {
        return classList;
    }
}
