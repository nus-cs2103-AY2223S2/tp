package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the patient records.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Nric nric;
    private final Name name;
    private final DateOfBirth date;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final DrugAllergy drugAllergy;
    private final Doctor doctor;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Medicine> medicines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Nric nric, Name name, DateOfBirth date, Phone phone,
                  Email email, Address address, DrugAllergy drugAllergy,
                  Gender gender, Doctor doctor,
                  Set<Tag> tags, Set<Medicine> medicines) {
        requireAllNonNull(name, phone, email, address, tags);
        this.nric = nric;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.drugAllergy = drugAllergy;
        this.doctor = doctor;
        this.tags.addAll(tags);
        this.medicines.addAll(medicines);
    }

    public Nric getNric() {
        return nric;
    }

    public Name getName() {
        return name;
    }

    public DateOfBirth getDate() {
        return date;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public DrugAllergy getDrugAllergy() {
        return drugAllergy;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable medicine set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Medicine> getMedicines() {
        return Collections.unmodifiableSet(medicines);
    }

    /**
     * Returns true if both persons have the same nric.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
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
        return otherPerson.getNric().equals(getNric())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getDrugAllergy().equals(getDrugAllergy())
                && otherPerson.getDoctor().equals(getDoctor())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getMedicines().equals(getMedicines());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, name, phone, email, address, drugAllergy, doctor, tags, medicines);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
                .append("; Name: ")
                .append(getName())
                .append("; DOB: ")
                .append(getDate())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Gender: ")
                .append(getGender())
                .append("; Attending Doctor: ")
                .append(getDoctor())
                .append("; Drug Allergy: ")
                .append(getDrugAllergy());

        Set<Tag> tags = getTags();
        Set<Medicine> medicine = getMedicines();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        if (!medicines.isEmpty()) {
            builder.append("; Medicines: ");
            medicines.forEach(builder::append);
        }
        return builder.toString();
    }

}
