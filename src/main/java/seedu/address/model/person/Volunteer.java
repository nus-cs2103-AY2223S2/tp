package seedu.address.model.person;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Volunteer extends Person {
    private final Nric nric;
    private final Age age;
    public Volunteer(Name name, Phone phone, Email email,
                     Address address, Nric nric, Age age, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.nric = nric;
        this.age = age;
    }

    public Nric getNric() {
        return nric;
    }

    public Age getAge() {
        return age;
    }

    public boolean isSamePerson(Volunteer otherVolunteer) {
        if (this == otherVolunteer) {
            return true;
        }
        return super.isSamePerson(otherVolunteer) && getNric().equals(otherVolunteer.getNric());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Volunteer)) {
            return false;
        }
        Volunteer otherVolunteer = (Volunteer) other;
        return super.equals(otherVolunteer)
                && getNric().equals(otherVolunteer.getNric())
                && getAge().equals(otherVolunteer.getAge());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; NRIC: ")
                .append(getNric())
                .append("; Age: ")
                .append(getAge());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
