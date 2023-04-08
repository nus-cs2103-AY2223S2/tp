package seedu.connectus.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Represents a Person in the ConnectUS.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private Optional<Phone> phone;
    private Optional<Email> email;

    // Data fields
    private Optional<Address> address;
    private Set<Module> modules = new HashSet<>();
    private Set<Remark> remarks = new HashSet<>();
    private Set<Cca> ccas = new HashSet<>();
    private Set<Major> majors = new HashSet<>();
    private Optional<Birthday> birthday;

    // Social media fields
    private Optional<SocialMedia> socialMedia;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name, remarks, modules, ccas, majors);
        this.name = name;
        this.phone = Optional.empty();
        this.email = Optional.empty();
        this.address = Optional.empty();
        this.socialMedia = Optional.empty();
        this.birthday = Optional.empty();
    }

    /**
     * Copy constructor allowing additions to tag list.
     */
    public Person(Person toCopy, Set<Remark> remarks, Set<Module> modules,
                  Set<Cca> ccas, Set<Major> majors) {
        requireNonNull(toCopy);
        this.name = toCopy.name;
        this.phone = toCopy.phone;
        this.email = toCopy.email;
        this.address = toCopy.address;
        this.socialMedia = toCopy.socialMedia;
        this.birthday = toCopy.birthday;
        this.remarks.addAll(remarks);
        this.modules.addAll(modules);
        this.ccas.addAll(ccas);
        this.majors.addAll(majors);
    }

    public void setPhone(Phone phone) {
        this.phone = Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = Optional.ofNullable(address);
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = Optional.ofNullable(socialMedia);
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = Optional.ofNullable(birthday);
    }

    public void setRemarks(Set<Remark> remarks) {
        this.remarks = remarks;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public void setCcas(Set<Cca> ccas) {
        this.ccas = ccas;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Birthday> getBirthday() {
        return birthday;
    }

    public Optional<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public String getAllFieldsAsString() {
        return String.format("%s %s %s %s %s %s %s %s %s %s",
                name, phone.map(Phone::toString).orElse(""),
                email.map(Email::toString).orElse(""),
                address.map(Address::toString).orElse(""),
                birthday.map(Birthday::toString).orElse(""),
                socialMedia.map(SocialMedia::getHandles).orElse(""),
                remarks, modules, ccas, majors);
    }

    /**
     * Returns an immutable remark set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Remark> getRemarks() {
        return Collections.unmodifiableSet(remarks);
    }

    /**
     * Returns an immutable module set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Returns an immutable cca set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Cca> getCcas() {
        return Collections.unmodifiableSet(ccas);
    }

    /**
     * Returns an immutable ccaPosition set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Major> getMajors() {
        return Collections.unmodifiableSet(majors);
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
                && otherPerson.getRemarks().equals(getRemarks())
                && otherPerson.getModules().equals(getModules())
                && otherPerson.getCcas().equals(getCcas())
                && otherPerson.getMajors().equals(getMajors());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remarks, modules, ccas, majors);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Social media: ")
                .append(getSocialMedia())
                .append("; Address: ")
                .append(getAddress());

        Set<Remark> remarks = getRemarks();
        if (!remarks.isEmpty()) {
            builder.append("; Remarks: ");
            remarks.forEach(builder::append);
        }
        Set<Module> modules = getModules();
        if (!modules.isEmpty()) {
            builder.append("; Modules: ");
            modules.forEach(builder::append);
        }
        Set<Cca> ccas = getCcas();
        if (!ccas.isEmpty()) {
            builder.append("; Ccas: ");
            ccas.forEach(builder::append);
        }
        Set<Major> majors = getMajors();
        if (!majors.isEmpty()) {
            builder.append("; Majors: ");
            majors.forEach(builder::append);
        }
        return builder.toString();
    }

}
