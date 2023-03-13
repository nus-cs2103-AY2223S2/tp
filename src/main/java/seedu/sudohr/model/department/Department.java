package seedu.sudohr.model.department;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.sudohr.model.employee.Person;

/**
 * Represents a Department in SudoHR.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Department {
    private final DepartmentName name;
    private final Set<Person> employees = new HashSet<>(); // To convert to UniqueList

    public Department(DepartmentName name) {
        this.name = name;
    }

    /**
     * Conversion from JSON to Department
     * @param name name of department
     * @param employees employees in the department
     */
    public Department(DepartmentName name, Set<Person> employees) {
        this.name = name;
        this.employees.addAll(employees);
    }

    public DepartmentName getName() {
        return name;
    }

    public Set<Person> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }

    /**
     * Adds an employee to the department.
     * The employee must not already exist in the department.
     */
    public void addEmployee(Person e) {
        employees.add(e);
    }

    /**
     * Removes {@code key} from this {@code sudohrBook}.
     * {@code key} must exist in the department.
     */
    public void removeEmployee(Person key) {
        employees.remove(key);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        return builder.toString();
    }

    /**
     * Equality only compares by department name.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Department // instanceof handles nulls
                && name.equals(((Department) other).name));
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
