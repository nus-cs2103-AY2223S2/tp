package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

enum Status {
    APPLIED,
    SHORTLISTED,
    ACCEPTED,
    REJECTED
}

/**
 * Represents an applicant in HRHero
 */
public class Applicant extends Person {
    //Applicant status
    Status status;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.status = Status.APPLIED;
    }
}