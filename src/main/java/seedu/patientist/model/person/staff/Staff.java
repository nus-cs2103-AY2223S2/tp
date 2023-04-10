package seedu.patientist.model.person.staff;

import java.util.Set;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.tag.RoleTag;
import seedu.patientist.model.tag.Tag;

/**
 * Represents a Staff object in Patientist
 * Guarantees: superclass guarantees
 */
public class Staff extends Person {
    /** Tag used to check if a person is a staff member. */
    public static final RoleTag STAFF_TAG = new RoleTag("Staff");

    /**
     * Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, IdNumber id, Address address, Set<Tag> tags) {
        super(name, phone, email, id, address, tags); //TODO: wards in charge implemented as tags for now
    }

    @Override
    public RoleTag getRoleTag() {
        return STAFF_TAG;
    }

    @Override
    public boolean isSamePerson(Person otherStaff) {
        if (!(otherStaff instanceof Staff)) {
            return false;
        }

        return super.isSamePerson(otherStaff);
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
        return super.equals(otherStaff);
    }
}
