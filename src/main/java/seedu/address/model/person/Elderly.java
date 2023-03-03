package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;

/**
 * Represents an Elderly in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Elderly extends Person {

    private final Nric nric;
    private final Age age;

    private final RiskLevel riskLevel;

    // private final Region region;

    // private final Community community;

    /**
     * Every field must be present and not null.
     */
    public Elderly(Name name, Phone phone, Email email,
                   Address address, Nric nric, Age age, RiskLevel riskLevel, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.nric = nric;
        this.age = age;
        this.riskLevel = riskLevel;
    }

    public Nric getNric() {
        return nric;
    }

    public Age getAge() {
        return age;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    /**
     * Returns true if both elderly have the same name and NRIC.
     * This defines a weaker notion of equality between two elderly.
     * Used to detect if the new elderly is "Duplicate" of existing ones
     * in the database. If duplicate, cannot add.
     */
    // Two elderly are the same if same name and age
    public boolean isSamePerson(Elderly otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if both elderly have the same identity and data fields.
     * This defines a stronger notion of equality between two elderly.
     * But is currently not used anywhere.
     */
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
