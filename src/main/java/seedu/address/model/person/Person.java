package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
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
    private final Age age;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<AvailableDate> availableDates = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Nric nric, Age age, Set<Tag> tags, Set<AvailableDate> availableDates) {

        requireAllNonNull(name, phone, email, address, tags, availableDates);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nric = nric;
        this.age = age;
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

    public Age getAge() {
        return age;
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
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getNric().equals(getNric());
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
                && otherPerson.getAge().equals(age)
                && otherPerson.getTags().equals(tags)
                && otherPerson.getAvailableDates().equals(availableDates);
    }

}
