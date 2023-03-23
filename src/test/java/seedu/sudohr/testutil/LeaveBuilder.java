package seedu.sudohr.testutil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * A utility class to help with building Leave objects.
 */

public class LeaveBuilder {

    public static final String DEFAULT_DATE = "2022-03-21";

    private LeaveDate date;
    private Set<Employee> employees;

    /**
     * Creates a {@code LeaveBuilder} with the default details.
     */
    public LeaveBuilder() {
        date = new LeaveDate(LocalDate.parse(DEFAULT_DATE));
        employees = new HashSet<>();
    }

    /**
     * Initializes the LeaveBuilder with the data of {@code leaveToCopy}.
     */
    public LeaveBuilder(Leave leaveToCopy) {
        date = leaveToCopy.getDate();
        employees = new HashSet<>(leaveToCopy.getEmployees());
    }

    /**
     * Sets the {@code LeaveDate} of the {@code Leave} that we are building.
     */
    public LeaveBuilder withLeaveDate(String date) {
        this.date = new LeaveDate(LocalDate.parse(date));
        return this;
    }

    /**
     * Parses the {@code employees} into a {@code Set<Employee>} and set it to the {@code Leave} that we are
     * building.
     */
    public LeaveBuilder withEmployees(Employee... employees) {
        this.employees.addAll(Arrays.asList(employees));
        return this;
    }

    public Leave build() {
        return new Leave(date, employees);
    }

}
