package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.tag.Tag;
/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    // Identity fields

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Set<Tag> tags) {
        super(name, phone, email, tags);
        requireAllNonNull(name, phone, email, tags);
    }
}
