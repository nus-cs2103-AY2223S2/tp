package seedu.address.model.entity.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A technician is a special type of staff, in which they handle the works on the vehicle.
 */
public class Technician extends Staff {
    /**
     * {@inheritDoc}
     */
    public Technician(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }
}
