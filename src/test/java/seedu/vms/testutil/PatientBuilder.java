package seedu.vms.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;
import seedu.vms.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_DOB = "1998-05-22";
    public static final String DEFAULT_BLOODTYPE = "A-";

    private Name name;
    private Phone phone;
    private Dob dateOfBirth;
    private BloodType bloodType;
    private Set<GroupName> allergies = new HashSet<>();
    private Set<GroupName> vaccines = new HashSet<>();

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        dateOfBirth = new Dob(DEFAULT_DOB);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        allergies = new HashSet<>();
        vaccines = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        dateOfBirth = patientToCopy.getDob();
        bloodType = patientToCopy.getBloodType();
        allergies = new HashSet<>(patientToCopy.getAllergy());
        vaccines = new HashSet<>(patientToCopy.getVaccine());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Allergy>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withAllergies(String... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Parses the {@code vaccines} into a {@code Set<Vaccine>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withVaccines(String... vaccines) {
        this.vaccines = SampleDataUtil.getVaccineSet(vaccines);
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
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
     * Sets the {@code Dob} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDob(String dateOfBirth) {
        this.dateOfBirth = new Dob(dateOfBirth);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, dateOfBirth, bloodType, allergies, vaccines);
    }

}
