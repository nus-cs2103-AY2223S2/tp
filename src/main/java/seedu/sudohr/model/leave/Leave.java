package seedu.sudohr.model.leave;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.UniqueEmployeeList;

/**
 * Represents a Leave in SudoHR.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Leave implements Comparable<Leave> {

    private final LeaveDate date;
    private final UniqueEmployeeList employees;

    /**
     * Every field must be present and not null.
     */
    public Leave(LeaveDate date) {
        this.date = date;
        this.employees = new UniqueEmployeeList();
    }

    /**
     * Creates a new leave with the specified date {@code date} and employees{@code employees}
     */
    public Leave(LeaveDate date, UniqueEmployeeList employees) {
        this.date = date;
        this.employees = employees;
    }

    /**
     * Creates a new leave with the specified date {@code date} and employees{@code employees}
     */
    public Leave(LeaveDate date, Set<Employee> employees) {
        this.date = date;
        this.employees = new UniqueEmployeeList();
        this.employees.addAll(employees);
    }

    public LeaveDate getDate() {
        return date;
    }

    /**
     * Get an employee by ID
     */
    public Employee getEmployee(Id id) {
        return employees.get(id);
    }

    /**
     * Returns unmodifiable set of employees.
     */
    public List<Employee> getEmployees() {
        return employees.asUnmodifiableObservableList();
    }

    /**
     * Gets the number of employees on leave on this date.
     */
    public int getNumberOnLeave() {
        return employees.size();
    }

    /**
     * Returns true if leave has a specific employee {@code employee} as an attendee.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Adds a specific employee {@code employee} to the leave.
     */
    public void addEmployee(Employee employee) {
        requireNonNull(employee);
        employees.add(employee);
    }

    /**
     * Replace an employee details with its updated details in the list.
     */
    public void setEmployee(Employee employeeToEdit, Employee editedEmployee) {
        employees.setEmployee(employeeToEdit, editedEmployee);
    }

    /**
     * Deletes a specific employee {@code employee} from the leave.
     */
    public void deleteEmployee(Employee employee) {
        requireNonNull(employee);
        employees.remove(employee);
    }

    /**
     * Returns true if both leave have the same date.
     */
    public boolean isSameLeave(Leave otherLeave) {

        if (otherLeave == this) {
            return true;
        }
        return otherLeave != null
                && otherLeave.getDate().equals(this.getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date);
    }

    /**
     * Equality only compares by leave date.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Leave // instanceof handles nulls
                        && date.equals(((Leave) other).date));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("leave on ")
                .append(getDate());
        return builder.toString();
    }

    @Override
    public int compareTo(Leave o) {
        return this.date.compareTo(o.date);
    }
}
