package seedu.sudohr.model.department;

import seedu.sudohr.model.person.Person;
import seedu.sudohr.model.person.UniquePersonList;

import static java.util.Objects.requireNonNull;

public class Department {
    private final DepartmentName name;
    private final UniquePersonList employees;

    public Department(DepartmentName name) {
        this.name = name;
        this.employees = new UniquePersonList();
    }

    public DepartmentName getName() {
        return name;
    }

    // Placeholder for creating department from storage

    /**
     * Adds an employee to the department.
     * The person must not already exist in the department.
     */
    public void addEmployee(Person e) {
        employees.add(e);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the department.
     * The person identity of {@code editedPerson} must not be the same as another existing employee in the department.
     */
    public void setEmployee(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        employees.setPerson(target, editedPerson);
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
