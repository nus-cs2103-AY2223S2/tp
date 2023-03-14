package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
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

    private final RiskLevel riskLevel;

    // private final Region region;

    // private final Community community;


    /**
     * Every field must be present and not null.
     */
    public Elderly(Name name, Phone phone, Email email, Address address,
                   Nric nric, Age age, RiskLevel riskLevel, Set<Tag> tags) {
        this(name, phone, email, address, nric, age, riskLevel, tags, new HashSet<>());
    }


    /**
     * Every field must be present and not null.
     */
    public Elderly(Name name, Phone phone, Email email, Address address,
            Nric nric, Age age, RiskLevel riskLevel, Set<Tag> tags, Set<AvailableDate> availableDates) {
        super(name, phone, email, address, nric, age, tags, availableDates);
        this.riskLevel = riskLevel;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
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

        Elderly otherElderly = (Elderly) other;
        return super.equals(otherElderly)
                && otherElderly.getRiskLevel().equals(riskLevel);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(),
                getNric(), getAge(), getRiskLevel(), getTags());
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
                .append(getRiskLevel())
                .append("; Dates Available: ")
                .append(getAvailableDates());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
