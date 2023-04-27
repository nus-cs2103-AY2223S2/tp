package trackr.model.person;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import trackr.model.ModelEnum;
import trackr.model.commons.Tag;
import trackr.model.item.Item;

/**
 * Represents a Person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
//@@author liumc-sg-reused
public abstract class Person extends Item {

    // Identity fields
    private final PersonName personName;
    private final PersonPhone personPhone;
    private final PersonEmail personEmail;

    // Data fields
    private final PersonAddress personAddress;
    private final Set<Tag> personTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(PersonName personName, PersonPhone personPhone, PersonEmail personEmail, PersonAddress personAddress,
                  Set<Tag> personTags, ModelEnum modelEnum) {
        super(modelEnum);
        requireAllNonNull(personName, personPhone, personEmail, personAddress, personTags);
        this.personName = personName;
        this.personPhone = personPhone;
        this.personEmail = personEmail;
        this.personAddress = personAddress;
        this.personTags.addAll(personTags);
    }

    public PersonName getPersonName() {
        return personName;
    }

    public PersonPhone getPersonPhone() {
        return personPhone;
    }

    public PersonEmail getPersonEmail() {
        return personEmail;
    }

    public PersonAddress getPersonAddress() {
        return personAddress;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getPersonTags() {
        return Collections.unmodifiableSet(personTags);
    }

    /**
     * Returns true if both people have the same name.
     * This defines a weaker notion of equality between two people.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) otherItem;

        return otherPerson != null
                && otherPerson.getPersonPhone().equals(getPersonPhone());
    }

    /**
     * Returns true if both people have the same identity and data fields.
     * This defines a stronger notion of equality between two people.
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
        return otherPerson.getPersonName().equals(getPersonName())
                && otherPerson.getPersonPhone().equals(getPersonPhone())
                && otherPerson.getPersonEmail().equals(getPersonEmail())
                && otherPerson.getPersonAddress().equals(getPersonAddress())
                && otherPerson.getPersonTags().equals(getPersonTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(personName, personPhone, personEmail, personAddress, personTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPersonName())
                .append("; Phone: ")
                .append(getPersonPhone())
                .append("; Email: ")
                .append(getPersonEmail())
                .append("; Address: ")
                .append(getPersonAddress());

        Set<Tag> tags = getPersonTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
