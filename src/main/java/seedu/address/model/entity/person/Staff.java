package seedu.address.model.entity.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * This class represents a staff.
 * There may be different types of staff.
 */
public abstract class Staff extends Person {
    /**
     * {@inheritDoc}
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }
}
