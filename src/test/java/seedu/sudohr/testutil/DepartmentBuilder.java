package seedu.sudohr.testutil;

import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_FIRST;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;

/**
 * A utility class to help with building Department objects.
 */

public class DepartmentBuilder {

    public static final String DEFAULT_NAME = DEPARTMENT_NAME_FIRST.fullName;

    private DepartmentName name;
    private Set<Employee> employees;

    /**
     * Creates a {@code DepartmentBuilder} with the default details.
     */
    public DepartmentBuilder() {
        name = new DepartmentName(DEFAULT_NAME);
        employees = new HashSet<>();
    }

    /**
     * Initializes the DepartmentBuilder with the data of {@code departmentToCopy}.
     */
    public DepartmentBuilder(Department departmentToCopy) {
        name = departmentToCopy.getName();
        employees = new HashSet<>(departmentToCopy.getEmployees());
    }

    /**
     * Sets the {@code DepartmentName} of the {@code Department} that we are building.
     */
    public DepartmentBuilder withDepartmentName(String name) {
        this.name = new DepartmentName(name);
        return this;
    }

    /**
     * Parses the {@code employees} into a {@code Set<Employee>} and set it to the {@code Department} that we are
     * building.
     */
    public DepartmentBuilder withEmployees(Employee... employees) {
        this.employees.addAll(Arrays.asList(employees));
        return this;
    }

    public Department build() {
        return new Department(name, employees);
    }

}
