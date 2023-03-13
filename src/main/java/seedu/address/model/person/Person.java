package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Indexing fields
    private final ContactIndex contactIndex;

    // Data fields
    private final Address address;
    private final GroupTagSet groupTags = new GroupTagSet();
    private final ModuleTagSet moduleTags = new ModuleTagSet();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, TelegramHandle telegramHandle,
                  ContactIndex contactIndex, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        requireAllNonNull(name, phone, email, address, telegramHandle, contactIndex, groupTags, moduleTags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.telegramHandle = telegramHandle;
        this.contactIndex = contactIndex;
        this.groupTags.addAll(groupTags);
        this.moduleTags.addAll(moduleTags);
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

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public ContactIndex getContactIndex() {
        return contactIndex;
    }

    /**
     * Returns a copy of the person's group tags.
     */
    public GroupTagSet getGroupTags() {
        return groupTags;
    }

    /**
     * Returns an immutable group tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GroupTag> getImmutableGroupTags() {
        return groupTags.getImmutableGroups();
    }

    /**
     * Returns a copy of the person's module tags.
     */
    public ModuleTagSet getModuleTags() {
        return moduleTags;
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableModuleTags() {
        return moduleTags.getImmutableModules();
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableCommonModuleTags() {
        return Collections.unmodifiableSet(moduleTags.getImmutableCommonModules());
    }

    /**
     * Sets the common modules that the person has with the user.
     */
    public void setCommonModules(Set<ModuleTag> userModules) {
        moduleTags.setCommonModules(userModules);
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getImmutableGroupTags().equals(getImmutableGroupTags())
                && otherPerson.getTelegramHandle().equals(getTelegramHandle())
                && otherPerson.getImmutableModuleTags().equals(getImmutableModuleTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, telegramHandle, groupTags, moduleTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" [Index : " + String.valueOf(contactIndex) + "]")
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress())
                .append("\nTelegram: ")
                .append(getTelegramHandle())
                .append("\nGroups: ")
                .append(getImmutableGroupTags())
                .append("\nModules: ")
                .append(getImmutableModuleTags());

        return builder.toString();
    }

}
