package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in FriendlyLink.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Nric nric;
    private final BirthDate birthDate;
    private final Region region;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<AvailableDate> availableDates = new HashSet<>();

    /**
     * Constructs a new person.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Nric nric,
                  BirthDate birthDate, Region region, Set<Tag> tags, Set<AvailableDate> availableDates) {
        requireAllNonNull(name, phone, email, address, nric, birthDate, region, tags, availableDates);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nric = nric;
        this.birthDate = birthDate;
        this.region = region;
        this.tags.addAll(tags);
        this.availableDates.addAll(availableDates);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Nric getNric() {
        return nric;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Region getRegion() {
        return region;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    public Set<AvailableDate> getAvailableDates() {
        return Collections.unmodifiableSet(availableDates);
    }

    /**
     * Returns true if both persons have the same nric.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson Person to be compared to.
     * @return True if both persons have the same nric and false otherwise.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getNric().equals(nric);
    }

    /**
     * Returns true if both persons belong to the same region.
     * {@code otherPerson} must not be null.
     *
     * @param otherPerson Person to be compared to.
     * @return True if both persons belong to the same region and false otherwise.
     */
    public boolean isSameRegion(Person otherPerson) {
        requireNonNull(otherPerson);
        return otherPerson.getRegion().equals(region);
    }

    /**
     * Checks whether there are suitable available dates between two persons.
     * {@code otherPerson} must not be null.
     *
     * @param otherPerson Person to be compared to.
     * @return True if both persons share common available dates or at least one person
     *     has no specified available dates, false otherwise.
     */
    public boolean hasSuitableAvailableDates(Person otherPerson) {
        requireNonNull(otherPerson);
        Set<AvailableDate> availableDates = getAvailableDates();
        Set<AvailableDate> otherAvailableDates = otherPerson.getAvailableDates();

        // no restrictions
        if (availableDates.isEmpty() || otherAvailableDates.isEmpty()) {
            return true;
        }

        // find first matching dates
        for (AvailableDate date : availableDates) {
            for (AvailableDate availableDate : otherAvailableDates) {
                if (date.isIntersect(availableDate.getStartDate(), availableDate.getEndDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(name)
                && otherPerson.getPhone().equals(phone)
                && otherPerson.getEmail().equals(email)
                && otherPerson.getAddress().equals(address)
                && otherPerson.getNric().equals(nric)
                && otherPerson.getBirthDate().equals(birthDate)
                && otherPerson.getRegion().equals(region)
                && otherPerson.getTags().equals(tags)
                && otherPerson.getAvailableDates().equals(availableDates);
    }

}
