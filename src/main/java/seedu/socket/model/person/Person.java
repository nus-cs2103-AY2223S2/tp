package seedu.socket.model.person;

import static seedu.socket.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.socket.model.tag.Language;
import seedu.socket.model.tag.Tag;

/**
 * Represents a {@code Person} in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    public static final String CATEGORY_NAME = "name";
    public static final String CATEGORY_GITHUB = "github";
    public static final String CATEGORY_PHONE = "phone";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ADDRESS = "address";
    private static final String[] categories = {CATEGORY_NAME, CATEGORY_PHONE, CATEGORY_ADDRESS,
        CATEGORY_EMAIL, CATEGORY_GITHUB};
    public static final List<String> CATEGORIES = Arrays.asList(categories);
    // Identity fields
    /** {@code Name} associated with the {@code Person} instance. */
    private final Name name;

    // Data fields
    /** {@code GitHubProfile} associated with the {@code Person} instance. */
    private final GitHubProfile profile;
    /** {@code Phone} associated with the {@code Person} instance. */
    private final Phone phone;
    /** {@code Email} associated with the {@code Person} instance. */
    private final Email email;
    /** {@code Address} associated with the {@code Person} instance. */
    private final Address address;
    /** {@code Set<Language>} associated with the {@code Person} instance. */
    private final Set<Language> languages = new HashSet<>();
    /** {@code Set<Tag>} associated with the {@code Person} instance. */
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for an instance of {@code Person}. Every field must be present and not null.
     *
     * @param name {@code Name} associated with the {@code Person} instance.
     * @param profile {@code GitHubProfile} associated with the {@code Person} instance.
     * @param phone {@code Phone} associated with the {@code Person} instance.
     * @param email {@code Email} associated with the {@code Person} instance.
     * @param address {@code Address} associated with the {@code Person} instance.
     * @param languages {@code Set<Language>} associated with the {@code Person} instance.
     * @param tags {@code Set<Tag>} associated with the {@code Person} instance.
     */
    public Person(Name name, GitHubProfile profile, Phone phone, Email email, Address address, Set<Language> languages,
                  Set<Tag> tags) {
        requireAllNonNull(name, profile, phone, email, address, languages, tags);
        this.name = name;
        this.profile = profile;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.languages.addAll(languages);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public GitHubProfile getProfile() {
        return profile;
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

    /**
     * Returns an immutable {@code Language} set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable {@code Language} set.
     */
    public Set<Language> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    /**
     * Returns an immutable {@code Tag} set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable {@code Tag} set.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson {@code Person} to compare with.
     * @return {@code true} if both persons have the same name.
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
     *
     * @param other {@code Object} to compare with.
     * @return {@code true} if both persons have the same identity and data fields.
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
                && otherPerson.getProfile().equals(getProfile())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getLanguages().equals(getLanguages())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, profile, phone, email, address, languages, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Profile: ")
                .append(getProfile())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Language> languages = getLanguages();
        if (!languages.isEmpty()) {
            builder.append("; Languages: ");
            languages.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
