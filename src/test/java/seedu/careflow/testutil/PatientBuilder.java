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

    public static final String DEFAULT_NAME = "John Doe";
    public static final String DEFAULT_PHONE = "98765432";
    public static final String DEFAULT_EMAIL = "johnd@example.com";
    public static final String DEFAULT_ADDRESS = "John Street, Block 123, #01-01";
    public static final String DEFAULT_BIRTHDATE = "21-01-2000";
    public static final String DEFAULT_GENDER = "male";
    public static final String DEFAULT_IC = "T3871918C";
    public static final String DEFAULT_DRUGALLERGY = "Aspirin";
    public static final String DEFAULT_EMERGENCYCONTACT = "93746552";

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
        drugAllergy = new DrugAllergy(DEFAULT_DRUGALLERGY);
        emergencyContact = new Phone(DEFAULT_EMERGENCYCONTACT);
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
