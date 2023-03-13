package seedu.address.model.entity.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * This class represents a staff.
 * There may be different types of staff.
 */
public abstract class Staff extends Person {
    private final int id;

    /**
     * {@inheritDoc}
     */
    public Staff(int id, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.id = id;
    }

    /**
     * This method returns Staff id.
     * @return staff id.
     */
    public int getId() {
        return id;
    }
}
