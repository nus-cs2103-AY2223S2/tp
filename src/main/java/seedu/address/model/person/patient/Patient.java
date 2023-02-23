package seedu.address.model.person.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Represents a Patient in the patient record
 */
public class Patient extends Person {
    private final DateOfBirth birthDate;
    private final Gender gender;
    private final Ic ic;
    private Optional<DrugAllergy> drugAllergy;
    private Optional<EmergencyContact> emergencyContact;

    /**
     * Constructs a {@code DateOfBirth}.
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, DateOfBirth birthDate, Gender gender, Ic ic) {
        super(name, phone, email, address);
        requireAllNonNull(birthDate, gender, ic);
        this.birthDate = birthDate;
        this.gender = gender;
        this.ic = ic;
    }

    /**
     * Constructs a {@code DateOfBirth}.
     * Uses this constructor when want to include drug allergy or emergency contact of the patient.
     */
    public Patient(Name name, Phone phone, Email email, Address address, DateOfBirth birthDate, Gender gender, Ic ic,
        DrugAllergy drugAllergy, EmergencyContact emergencyContact) {
        this(name, phone, email, address, birthDate, gender, ic);
        this.drugAllergy = Optional.ofNullable(drugAllergy);
        this.emergencyContact = Optional.ofNullable(emergencyContact);
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

    public EmergencyContact getEmergencyContact() {
        return this.emergencyContact.orElse(null);
    }
}
