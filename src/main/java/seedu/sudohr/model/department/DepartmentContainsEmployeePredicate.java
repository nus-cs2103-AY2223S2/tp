package seedu.sudohr.model.department;

import java.util.function.Predicate;

import seedu.sudohr.model.employee.Id;

/**
 * Tests that a {@code Department}'s {@code employees} contains the employee given.
 */
public class DepartmentContainsEmployeePredicate implements Predicate<Department> {
    private final Id id;

    public DepartmentContainsEmployeePredicate(Id id) {
        this.id = id;
    }

    public Id getId() {
        assert id != null : "Predicate should not accept null value!";
        return id;
    }

    @Override
    public boolean test(Department department) {
        return department.hasEmployee(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DepartmentContainsEmployeePredicate // instanceof handles nulls
                && id.equals(((DepartmentContainsEmployeePredicate) other).id)); // state check
    }

}
