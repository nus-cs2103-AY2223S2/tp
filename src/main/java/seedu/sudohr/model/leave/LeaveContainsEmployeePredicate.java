package seedu.sudohr.model.leave;

import java.util.Set;
import java.util.function.Predicate;

import seedu.sudohr.model.employee.Employee;

/**
 * Tests that a {@code Employee}'s exists in a set of {@code employeesOnLeave}.
 */
public class LeaveContainsEmployeePredicate implements Predicate<Employee> {
    private final Set<Employee> employeesOnLeave;

    public LeaveContainsEmployeePredicate(Set<Employee> employeesOnLeave) {
        this.employeesOnLeave = employeesOnLeave;
    }

    @Override
    public boolean test(Employee employee) {
        return employeesOnLeave.stream()
                .anyMatch(employeeInList -> employeeInList.equals(employee));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeaveContainsEmployeePredicate // instanceof handles nulls
                        && employeesOnLeave.equals(((LeaveContainsEmployeePredicate) other).employeesOnLeave));
    }
}
