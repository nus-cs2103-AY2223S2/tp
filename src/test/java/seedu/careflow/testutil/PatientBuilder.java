package seedu.careflow.testutil;

import seedu.careflow.model.patient.Address;
import seedu.careflow.model.patient.DateOfBirth;
import seedu.careflow.model.patient.DrugAllergy;
import seedu.careflow.model.patient.Email;
import seedu.careflow.model.patient.Gender;
import seedu.careflow.model.patient.Ic;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.Phone;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDATE = "01-01-1990";
    public static final String DEFAULT_GENDER = "female";
    public static final String DEFAULT_IC = "A7654321B";
    public static final String DEFAULT_DRUG_ALLERGY = "penicillin";
    public static final String DEFAULT_EMERGENCY_CONTACT = "88888888";
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private DateOfBirth dateOfBirth;
    private Gender gender;
    private Ic ic;
    // optional fields
    private DrugAllergy drugAllergy;
    private Phone emergencyContact;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateOfBirth = new DateOfBirth(DEFAULT_BIRTHDATE);
        gender = new Gender(DEFAULT_GENDER);
        ic = new Ic(DEFAULT_IC);
        drugAllergy = new DrugAllergy(DEFAULT_DRUG_ALLERGY);
        emergencyContact = new Phone(DEFAULT_EMERGENCY_CONTACT);
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        dateOfBirth = patientToCopy.getBirthDate();
        gender = patientToCopy.getGender();
        ic = patientToCopy.getIc();
        drugAllergy = patientToCopy.getDrugAllergy();
        emergencyContact = patientToCopy.getEmergencyContact();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDob(String dob) {
        this.dateOfBirth = new DateOfBirth(dob);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Patient} that we are building.
     */
    public PatientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Ic} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    /**
     * Sets the {@code DrugAllergy} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDrugAllergy(String drugAllergy) {
        if (drugAllergy == null) {
            this.drugAllergy = null;
        } else {
            this.drugAllergy = new DrugAllergy(drugAllergy);
        }
        return this;
    }

    /**
     * Sets the {@code EmergencyContact} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmergencyContact(String emergencyContact) {
        if (emergencyContact == null) {
            this.emergencyContact = null;
        } else {
            this.emergencyContact = new Phone(emergencyContact);
        }
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, address, dateOfBirth, gender, ic, drugAllergy, emergencyContact);
    }
}
