package seedu.careflow.model.person;

import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Patient in the patient recordn
 */
public class Patient {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private DateOfBirth birthDate = null;
    private Gender gender = null;
    private Ic ic = null;
    private Optional<DrugAllergy> drugAllergy;
    private Optional<Phone> emergencyContact;

    // private constructor that used by other constructor to set up basic patient's field
    private Patient(Name name, Phone phone, Email email, Address address) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Constructs a {@code DateOfBirth}.
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, DateOfBirth birthDate, Gender gender, Ic ic) {
        this(name, phone, email, address);
        requireAllNonNull(birthDate, gender, ic);
        this.birthDate = birthDate;
        this.gender = gender;
        this.ic = ic;
        // initialise optional field: drugAllergy and emergencyContact
        this.drugAllergy = Optional.empty();
        this.emergencyContact = Optional.empty();
    }

    /**
     * Constructs a {@code DateOfBirth}.
     * Uses this constructor when want to include drug allergy or emergency contact of the patient.
     */
    public Patient(Name name, Phone phone, Email email, Address address, DateOfBirth birthDate, Gender gender, Ic ic,
        DrugAllergy drugAllergy, Phone emergencyContact) {
        this(name, phone, email, address, birthDate, gender, ic);
        this.drugAllergy = Optional.ofNullable(drugAllergy);
        this.emergencyContact = Optional.ofNullable(emergencyContact);
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

    public DateOfBirth getBirthDate() {
        return this.birthDate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Ic getIc() {
        return this.ic;
    }

    public DrugAllergy getDrugAllergy() {
        return this.drugAllergy.orElse(null);
    }

    public Phone getEmergencyContact() {
        return this.emergencyContact.orElse(null);
    }

    /**
     * Returns true if both patient have the same name.
     * This defines a weaker notion of equality between two patient.
     */
    public boolean isSamePerson(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPerson = (Patient) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBirthDate().equals(getBirthDate())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getIc().equals(getIc());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthDate, gender, ic);
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
                .append("; Date of Birth: ")
                .append(getBirthDate())
                .append("; Gender: ")
                .append(getGender())
                .append("; IC: ")
                .append(getIc());

        if (drugAllergy.isPresent()) {
            builder.append("; Drug Allergy: ").append(getDrugAllergy());
        }
        if (emergencyContact.isPresent()) {
            builder.append("; Emergency Contact Number: ").append(getEmergencyContact());
        }

        return builder.toString();
    }
}
