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
    private final Specialty specialty;
    private final Yoe yoe;
    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Specialty specialty, Yoe yoe, Set<Tag> tags) {
        super(name, phone, email, tags);
        requireAllNonNull(name, phone, email, specialty, yoe, tags);
        this.specialty = specialty;
        this.yoe = yoe;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
    public Yoe getYoe() {
        return yoe;
    }
}
