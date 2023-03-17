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
     * Returns true if leave has a specific person {@code person} as an attendee.
     */
    public boolean hasPerson(Employee person) {
        requireNonNull(person);
        return attendees.contains(person);
    }

    /**
     * Adds a specific person {@code person} to the leave.
     */
    public void addPerson(Employee person) {
        requireNonNull(person);
        this.attendees.add(person);
    }

    /**
     * Deletes a specific person {@code person} from the leave.
     */
    public void deletePerson(Employee person) {
        requireNonNull(person);
        this.attendees.remove(person);
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
