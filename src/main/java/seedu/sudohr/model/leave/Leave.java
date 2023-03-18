package seedu.sudohr.model.leave;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.sudohr.model.employee.Employee;


/**
 * Represents a Leave in the sudohr book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Leave {

    private final Date date;
    private final Set<Employee> employees;

    /**
     * Every field must be present and not null.
     */
    public Leave(Date date) {
        this.date = date;
        this.employees = new HashSet<Employee>();
    }

    /**
     * Creates a new leave with the specified date {@code date} and employees{@code employees}
     */
    public Leave(Date date, Set<Employee> employees) {
        this.date = date;
        this.employees = employees;
    }

    public Date getDate() {
        return date;
    }

    public Set<Employee> getAttendees() {
        return employees;
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
        this.employees.add(employee);
    }

    /**
     * Deletes a specific employee {@code employee} from the leave.
     */
    public void deleteEmployee(Employee employee) {
        requireNonNull(employee);
        this.employees.remove(employee);
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(";Date: ")
                .append(getDate())
                .append("\n");
        return builder.toString();
    }
}
