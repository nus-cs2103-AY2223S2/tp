package seedu.sudohr.model.employee;

import java.util.function.Predicate;


/**
 * Tests that a {@code Employee}'s {@code ID} matches any existing employees' ID.
 */
public class FindByIdPredicate implements Predicate<Employee> {
    private final Id id;

    public FindByIdPredicate(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getId().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByIdPredicate // instanceof handles nulls
                && id.equals(((FindByIdPredicate) other).id)); // state check
    }
}
