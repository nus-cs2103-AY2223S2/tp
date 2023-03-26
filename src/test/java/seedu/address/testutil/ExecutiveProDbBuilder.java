package seedu.address.testutil;

import seedu.address.model.ExecutiveProDb;
import seedu.address.model.employee.Employee;

/**
 * A utility class to help with building ExecutiveProDb objects.
 * Example usage: <br>
 *     {@code ExecutiveProDb ab = new ExecutiveProDbBuilder().withPerson("John", "Doe").build();}
 */
public class ExecutiveProDbBuilder {

    private ExecutiveProDb executiveProDb;

    public ExecutiveProDbBuilder() {
        executiveProDb = new ExecutiveProDb();
    }

    public ExecutiveProDbBuilder(ExecutiveProDb executiveProDb) {
        this.executiveProDb = executiveProDb;
    }

    /**
     * Adds a new {@code Person} to the {@code ExecutiveProDb} that we are building.
     */
    public ExecutiveProDbBuilder withEmployee(Employee employee) {
        executiveProDb.addEmployee(employee);
        return this;
    }

    public ExecutiveProDb build() {
        return executiveProDb;
    }
}
