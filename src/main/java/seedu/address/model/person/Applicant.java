package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Represents an applicant in HRHero
 */
public class Applicant extends Person {
    //Applicant status
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Status status) {
        super(name, phone, email, address, tags);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getAddress().equals(getAddress())
                && otherApplicant.getNotes().equals(getNotes())
                && otherApplicant.getStatus().equals(getStatus());
    }
}