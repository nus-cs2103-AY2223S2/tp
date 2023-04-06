package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Optional<Remark> remark;
    private final Optional<Telegram> telegram;
    private final Optional<Education> education;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Education education, Remark remark,
            Telegram telegram, Set<Module> modules, Set<Tag> tags) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.address = Optional.ofNullable(address);
        this.education = Optional.ofNullable(education);
        this.remark = Optional.ofNullable(remark);
        this.telegram = Optional.ofNullable(telegram);
        this.tags.addAll(tags);
        this.modules.addAll(modules);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getOptionalPhone() {
        return phone;
    }

    public Optional<Email> getOptionalEmail() {
        return email;
    }

    public Optional<Address> getOptionalAddress() {
        return address;
    }

    public Optional<Education> getOptionalEducation() {
        return education;
    }

    public Optional<Remark> getOptionalRemark() {
        return remark;
    }

    public Optional<Telegram> getOptionalTelegram() {
        return telegram;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
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
                && otherPerson.getOptionalPhone().equals(getOptionalPhone())
                && otherPerson.getOptionalEmail().equals(getOptionalEmail())
                && otherPerson.getOptionalAddress().equals(getOptionalAddress())
                && otherPerson.getOptionalEducation().equals(getOptionalEducation())
                && otherPerson.getModules().equals(getModules())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getOptionalRemark().equals(getOptionalRemark())
                && otherPerson.getOptionalTelegram().equals(getOptionalTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, education, modules, remark, tags, telegram);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        getOptionalPhone().ifPresent(phone -> builder.append("; Phone: ").append(phone));
        getOptionalEmail().ifPresent(email -> builder.append("; Email: ").append(email));
        getOptionalAddress().ifPresent(address -> builder.append("; Address: ").append(address));
        getOptionalEducation().ifPresent(education -> builder.append("; Education: ").append(education));
        getOptionalTelegram().ifPresent(telegram -> builder.append("; Telegram: ").append(telegram));
        getOptionalRemark().ifPresent(remark -> builder.append("; Remark: ").append(remark));

        Set<Module> modules = getModules();
        if (!modules.isEmpty()) {
            builder.append(" ; Modules: ");
            modules.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
