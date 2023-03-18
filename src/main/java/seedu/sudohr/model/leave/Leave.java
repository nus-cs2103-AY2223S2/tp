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

    private final Date title;
    private final Set<Employee> attendees;

    /**
     * Every field must be present and not null.
     */
    public Leave(Date title) {
        this.title = title;
        this.attendees = new HashSet<Employee>();
    }

    /**
     * Creates a new leave with the specified title {@code title} and attendees{@code attendees}
     */
    public Leave(Date title, Set<Employee> attendees) {
        this.title = title;
        this.attendees = attendees;
    }

    public Date getTitle() {
        return title;
    }

    public Set<Employee> getAttendees() {
        return attendees;
    }

    /**
     * Returns true if leave has a specific employee {@code employee} as an attendee.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return attendees.contains(employee);
    }

    /**
     * Adds a specific employee {@code employee} to the leave.
     */
    public void addEmployee(Employee employee) {
        requireNonNull(employee);
        this.attendees.add(employee);
    }

    /**
     * Deletes a specific employee {@code employee} from the leave.
     */
    public void deleteEmployee(Employee employee) {
        requireNonNull(employee);
        this.attendees.remove(employee);
    }

    /**
     * Returns true if both leave have the same date.
     */
    public boolean isSameLeave(Leave otherLeave) {

        if (otherLeave == this) {
            return true;
        }
        return otherLeave != null
                && otherLeave.getTitle().equals(this.getTitle());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(";Date: ")
                .append(getTitle())
                .append("\n");
        return builder.toString();
    }
}
