package seedu.address.model.entity.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

public class Staff extends Person {
    /**
     * {@inheritDoc}
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }
}
