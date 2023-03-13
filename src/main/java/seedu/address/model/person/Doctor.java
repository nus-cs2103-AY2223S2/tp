package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    public Doctor(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags) {
        super(name, phone, email, nric, address, tags);
    }

    @Override
    public boolean isDoctor() {
        return true;
    }
}

