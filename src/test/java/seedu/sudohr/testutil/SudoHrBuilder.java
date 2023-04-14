package seedu.sudohr.testutil;

import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.employee.Employee;

/**
 * A utility class to help with building SudoHR objects.
 * Example usage: <br>
 *     {@code SudoHr sudoHr = new AddressBookBuilder().withEmployee("John", "Doe").build();}
 */
public class SudoHrBuilder {

    private SudoHr sudoHr;

    public SudoHrBuilder() {
        sudoHr = new SudoHr();
    }

    public SudoHrBuilder(SudoHr sudoHr) {
        this.sudoHr = sudoHr;
    }

    /**
     * Adds a new {@code Employee} to the {@code SudoHr} that we are building.
     */
    public SudoHrBuilder withEmployee(Employee employee) {
        sudoHr.addEmployee(employee);
        return this;
    }

    public SudoHr build() {
        return sudoHr;
    }
}
