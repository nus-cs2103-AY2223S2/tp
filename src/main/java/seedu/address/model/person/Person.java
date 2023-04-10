package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.DeepCopyable;

/**
 * Represents a Person in the E-Lister.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person implements DeepCopyable<Person> {

    // Identity fields
    private Name name;
    private Phone phone;
    private Email email;
    private Income income;
    // Data fields
    private Address address;
    private HashSet<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Income income, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.income = income;
        this.tags.addAll(tags);
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

    public Income getIncome() {
        return income;
    }

    /**
     * Adds a tag to the person.
     */
    public void addTag(Tag toAdd) {
        tags.add(toAdd);
    }

    /**
     * Deletes a tag from the person.
     *
     * @param toDelete the tag to deleted from the person.
     */
    public void deleteTag(Tag toDelete) {
        tags.remove(toDelete);
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Edits the fields of this {@code Person} according to {@code editPersonDescriptor}.
     *
     * @param editPersonDescriptor field edits to be made
     */
    public void editTo(EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        name = editPersonDescriptor.getName().orElse(name);
        phone = editPersonDescriptor.getPhone().orElse(phone);
        email = editPersonDescriptor.getEmail().orElse(email);
        address = editPersonDescriptor.getAddress().orElse(address);
        income = editPersonDescriptor.getIncome().orElse(income);
        tags = new HashSet<>(editPersonDescriptor.getTags().orElse(tags));
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
                && otherPerson.getName().equals(getName());
    }

    /**
     * Deep copies the current person.
     */
    public Person deepCopy() {
        return new Person(name, phone, email, address, income, tags);
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
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getIncome().equals(getIncome())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, income);
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
                .append("; Income: ")
                .append(getIncome());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
