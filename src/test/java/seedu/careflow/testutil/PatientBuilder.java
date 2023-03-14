package seedu.careflow.testutil;

import seedu.careflow.model.person.Address;
import seedu.careflow.model.person.DateOfBirth;
import seedu.careflow.model.person.DrugAllergy;
import seedu.careflow.model.person.Email;
import seedu.careflow.model.person.Gender;
import seedu.careflow.model.person.Ic;
import seedu.careflow.model.person.Name;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.person.Phone;

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

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private DateOfBirth dateOfBirth;
    private Gender gender;
    private Ic ic;
    // optional field
    private DrugAllergy drugAllergy;
    private Phone emergencyContact;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateOfBirth = new DateOfBirth(DEFAULT_BIRTHDATE);
        gender = new Gender(DEFAULT_GENDER);
        ic = new Ic(DEFAULT_IC);
        drugAllergy = null;
        emergencyContact = null;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PatientBuilder(Patient personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        dateOfBirth = personToCopy.getBirthDate();
        gender = personToCopy.getGender();
        drugAllergy = personToCopy.getDrugAllergy();
        emergencyContact = personToCopy.getEmergencyContact();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, address, dateOfBirth, gender, ic, drugAllergy, emergencyContact);
    }

}
