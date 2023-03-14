package seedu.patientist.model.person.staff;

import java.util.Set;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.tag.Tag;

/**
 * Represents a Staff object in Patientist
 * Guarantees: superclass guarantees
 * TODO: Implement this
 */
public class Staff extends Person {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Staff(Name name, Phone phone, Email email, IdNumber id, Address address, Set<Tag> tags) {
        super(name, phone, email, id, address, tags); //TODO: wards in charge implemented as tags for now
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Type: Staff ");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Staff)) {
            return false;
        }

        Staff otherStaff = (Staff) object;
        return otherStaff.getName().equals(getName())
                && otherStaff.getPhone().equals(getPhone())
                && otherStaff.getEmail().equals(getEmail())
                && otherStaff.getAddress().equals(getAddress())
                && otherStaff.getTags().equals(getTags());
    }
}
