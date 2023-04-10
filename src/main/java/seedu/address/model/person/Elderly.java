package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;

/**
 * Represents an Elderly in FriendlyLink.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Elderly extends Person {

    private final RiskLevel riskLevel;

    /**
     * Constructs a new Elderly.
     * Every field must be present and not null.
     */
    public Elderly(Name name, Phone phone, Email email, Address address, Nric nric, BirthDate birthDate,
                   Region region, RiskLevel riskLevel, Set<Tag> tags, Set<AvailableDate> availableDates) {
        super(name, phone, email, address, nric, birthDate, region, tags, availableDates);
        requireNonNull(riskLevel);
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
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(),
                getNric(), getBirthDate(), getRegion(), getRiskLevel(), getTags(), getAvailableDates());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; NRIC: ")
                .append(getNric())
                .append("; Age: ")
                .append(getBirthDate().getAge())
                .append(";\nPhone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Region: ")
                .append(getRegion())
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
