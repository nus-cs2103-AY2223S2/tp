package seedu.sudohr.model.leave;

import java.util.Set;
import java.util.function.Predicate;

import seedu.sudohr.model.employee.Employee;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class LeaveContainsEmployeePredicate implements Predicate<Employee> {
    private final Set<Employee> personsInEvent;

    public LeaveContainsEmployeePredicate(Set<Employee> personsInEvent) {
        this.personsInEvent = personsInEvent;
    }

    @Override
    public boolean test(Employee person) {
        return personsInEvent.stream()
                .anyMatch(personInList -> personInList.equals(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeaveContainsEmployeePredicate // instanceof handles nulls
                        && personsInEvent.equals(((LeaveContainsEmployeePredicate) other).personsInEvent));
    }
}
