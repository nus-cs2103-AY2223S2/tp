package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.*;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.person.information.NRIC;
import seedu.address.model.tag.Tag;

public class Elderly extends Person {

    private final NRIC nric;
    private final Age age;

    private final RiskLevel riskLevel;

    // private final Region region;

    // private final Community community;

    public Elderly(Name name, Phone phone, Email email,
                   Address address, NRIC nric, Age age, RiskLevel riskLevel, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.nric = nric;
        this.age = age;
        this.riskLevel = riskLevel;
    }

    public NRIC getNric() {
        return nric;
    }

    public Age getAge() {
        return age;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    // Two elderly are the same if same name and age
    public boolean isSamePerson(Elderly otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getAge().equals(getAge());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Elderly)) {
            return false;
        }

        Elderly otherPerson = (Elderly) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getNric().equals(getNric())
                && otherPerson.getAge().equals(getAge())
                && otherPerson.getRiskLevel().equals(getRiskLevel())
                && otherPerson.getTags().equals(getTags());
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
                .append(getAge())
                .append("; RiskLevel: ")
                .append(getRiskLevel());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}
