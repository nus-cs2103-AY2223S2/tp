package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NRIC = "S0000000Z";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DATE_OF_BIRTH = "28/11/2000";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GENDER = "Male";
    public static final String DEFAULT_DOCTOR = "Ben";
    public static final String DEFAULT_DRUG_ALLERGY = "NKDA";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Nric nric;
    private Name name;
    private DateOfBirth date;
    private Phone phone;
    private Email email;
    private Address address;
    private DrugAllergy drugAllergy;
    private Gender gender;
    private Doctor doctor;
    private Set<Tag> tags;
    private Set<Medicine> medicines;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        date = new DateOfBirth(DEFAULT_DATE_OF_BIRTH);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        drugAllergy = new DrugAllergy(DEFAULT_DRUG_ALLERGY);
        gender = new Gender(DEFAULT_GENDER);
        doctor = new Doctor(DEFAULT_DOCTOR);
        tags = new HashSet<>();
        medicines = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        nric = personToCopy.getNric();
        name = personToCopy.getName();
        date = personToCopy.getDate();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        drugAllergy = personToCopy.getDrugAllergy();
        gender = personToCopy.getGender();
        doctor = personToCopy.getDoctor();
        tags = new HashSet<>(personToCopy.getTags());
        medicines = new HashSet<>(personToCopy.getMedicines());
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Person} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = new DateOfBirth(date);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Doctor} of the {@code Person} that we are building.
     */
    public PersonBuilder withDoctor(String doctor) {
        this.doctor = new Doctor(doctor);
        return this;
    }

    /**
     * Parses the {@code medicines} into a {@code Set<Medicine>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMedicines(String... medicines) {
        this.medicines = SampleDataUtil.getMedicineSet(medicines);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withDrugAllergy(String drugAllergy) {
        this.drugAllergy = new DrugAllergy(drugAllergy);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Builds a person with the given attributes
     */
    public Person build() {
        return new Person(nric, name, date, phone, email, address,
                        drugAllergy, gender, doctor, tags, medicines);
    }

}
