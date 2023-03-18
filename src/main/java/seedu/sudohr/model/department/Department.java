package seedu.sudohr.model.department;

import java.util.Collections;
import java.util.Set;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.UniqueEmployeeList;

/**
 * Represents a Department in SudoHR.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Department {
    private final DepartmentName name;
    private final UniqueEmployeeList employees = new UniqueEmployeeList();

    public Department(DepartmentName name) {
        this.name = name;
    }

    /**
     * Conversion from JSON to Department
     * @param name name of department
     * @param employees employees in the department
     */
    public Department(DepartmentName name, Set<Employee> employees) {
        this.name = name;
        this.employees.addAll(employees);
    }

    /**
     * Returns the name of the department
     */
    public DepartmentName getName() {
        return name;
    }

    /**
     * Returns whether employee exists in the department.
     */
    public boolean hasEmployee(Employee e) {
        return employees.contains(e);
    }

    public boolean hasEmployee(Id id) {
        for (Employee employee: employees) {
            if (employee.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns unmodifiable set of employees.
     */
    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(employees.asSet());
    }

    /**
     * Adds an employee to the department.
     * The employee must not already exist in the department.
     */
    public void addEmployee(Employee e) {
        employees.add(e);
    }

    /**
     * Removes {@code key} from this {@code sudohrBook}.
     * {@code key} must exist in the department.
     */
    public void removeEmployee(Employee key) {
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
