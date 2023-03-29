package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private LocalDateTime time = null;

    private MedicalCondition medicalCondition;
    private Age age;
    private Appointment appointment;
    private Nric nric;

    /**
     * @param name the patient's name
     * @param phone the patient's phone number
     * @param email the patient's email address
     * @param address the patient's residential address
     * @param tags the patient's tag
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.medicalCondition = new MedicalCondition("");
        this.age = new Age("");
        this.nric = new Nric("");
    }

    /**
     * @param name the patient's name
     * @param phone the patient's phone number
     * @param email the patient's email address
     * @param address the patient's residential address
     * @param age the patient's age
     * @param tags the patient's tag
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.age = age;
        this.medicalCondition = new MedicalCondition("");
        this.nric = new Nric("");
    }

    /**
     * @param name             the patient's name
     * @param phone            the patient's phone number
     * @param email            the patient's phone email
     * @param address          the patient's address
     * @param tags             the patient's tags
     * @param time             the patient's appointment time
     * @param medicalCondition the patient's medical condition
     * @param age              the patient's age
     * @param nric             the patient's identity
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags,
                  LocalDateTime time, MedicalCondition medicalCondition, Appointment appointment, Nric nric) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.time = time;
        this.medicalCondition = medicalCondition;
        this.age = age;
        this.nric = nric;
        this.appointment = appointment;
    }

    /**
     * @param name             the patient's name
     * @param phone            the patient's phone number
     * @param email            the patient's phone email
     * @param address          the patient's address
     * @param tags             the patient's tags
     * @param medicalCondition the patient's medical condition
     * @param age              the patient's age
     * @param nric             the patient's identity
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags,
                  MedicalCondition medicalCondition, Nric nric) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
        this.age = age;
        this.nric = nric;
    }

    /**
     * @param name             the patient's name
     * @param phone            the patient's phone number
     * @param email            the patient's phone email
     * @param address          the patient's address
     * @param tags             the patient's tags
     * @param time             the patient's next appointment time
     * @param medicalCondition the patient's medical condition
     * @param age              the patient's age
     * @param nric             the patient's identity
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags,
                  LocalDateTime time, MedicalCondition medicalCondition, Nric nric) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
        this.time = time;
        this.nric = nric;
    }
    /**
     * @param name             the patient's name
     * @param phone            the patient's phone number
     * @param email            the patient's phone email
     * @param address          the patient's address
     * @param tags             the patient's tags
     * @param medicalCondition the patient's medical condition
     * @param age              the patient's age
     * @param nric             the patient's identity
     * @param appointment      the patient;s next appointment
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags,
                  MedicalCondition medicalCondition, Appointment appointment, Nric nric) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
        this.appointment = appointment;
        this.nric = nric;
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


    public LocalDateTime getTime() {
        return time;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Nric getNric() {
        return nric;
    }

    /**
     * check if the person has scheduled time.
     * @return true if the person has time.
     */
    public boolean hasTime() {
        return this.time != null;
    }


    public boolean hasAppointment() {
        return appointment != null;
    }

    public void markAppointment() {
        appointment = null;
    }

    // notice: after adding Appoinment, time attribute in person becomes invalid
    // therefore, isOnSearchDate should be done from Appointment side
    public boolean isOnSearchDate(LocalDate searchDate) {
        return appointment.isOnSearchDate(searchDate);
    }

    public boolean hasAge() {
        return this.age.getAge() != "";
    }

    public boolean hasNric() {
        return this.nric.getNumber() != "";
    }

    public boolean hasMedicalCondition() {
        return this.medicalCondition.getValue() != "";
    }

    public boolean hasClash(Person person) {
        return appointment.hasClash(person.getAppointment());
    }

    public Age getAge() {
        return age;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        Set<Tag> allTags = new HashSet<>(tags);
        return Collections.unmodifiableSet(allTags);
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
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Sets medical condition.
     *
     * @param medicalCondition the medical condition
     */
    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }
}
