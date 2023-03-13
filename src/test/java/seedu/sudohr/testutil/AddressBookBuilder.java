package seedu.sudohr.testutil;

import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.employee.Employee;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code SudoHr ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private SudoHr sudoHr;

    public AddressBookBuilder() {
        sudoHr = new SudoHr();
    }

    public AddressBookBuilder(SudoHr sudoHr) {
        this.sudoHr = sudoHr;
    }

    /**
     * Adds a new {@code Employee} to the {@code SudoHr} that we are building.
     */
    public AddressBookBuilder withPerson(Employee person) {
        sudoHr.addPerson(person);
        return this;
    }

    public SudoHr build() {
        return sudoHr;
    }
}
