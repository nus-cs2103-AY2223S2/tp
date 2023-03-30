package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
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
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Represents an Volunteer in FriendlyLink.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Volunteer extends Person {

    private final Set<MedicalQualificationTag> medicalTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Volunteer(Name name, Phone phone, Email email,
                     Address address, Nric nric, BirthDate birthDate,
                     Region region, Set<Tag> tags, Set<MedicalQualificationTag> medicalTags,
                     Set<AvailableDate> dateAvailabilities) {
        super(name, phone, email, address, nric, birthDate, region, tags, dateAvailabilities);
        requireNonNull(dateAvailabilities);
        this.medicalTags.addAll(medicalTags);
    }

    public Set<MedicalQualificationTag> getMedicalTags() {
        return Collections.unmodifiableSet(medicalTags);
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
        return super.equals(otherVolunteer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(),
                getNric(), getBirthDate(), getRegion(), getTags(), getMedicalTags(), getAvailableDates());
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
                .append("; Dates Available: ")
                .append(getAvailableDates())
                .append("; Region: ")
                .append(getRegion());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        Set<MedicalQualificationTag> medicalTags = getMedicalTags();
        if (!medicalTags.isEmpty()) {
            builder.append("; Medical qualifications: ");
            medicalTags.forEach(builder::append);
        }
        return builder.toString();
    }

}
