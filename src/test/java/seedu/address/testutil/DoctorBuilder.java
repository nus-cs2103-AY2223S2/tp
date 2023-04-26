package seedu.address.testutil;


import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.LoaderUtil;

/**
 * A utility class to help with building Doctor objects.
 */
public class DoctorBuilder {

    public static final String DEFAULT_NAME = "John Appleseed";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "johna@nuhs.org";
    public static final String DEFAULT_SPECIALTY = "General Medicine";
    public static final String DEFAULT_YOE = "5";

    private Name name;
    private Phone phone;
    private Email email;
    private Specialty specialty;
    private Yoe yoe;
    private Set<Tag> tags;
    private Set<Patient> patients;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        specialty = new Specialty(DEFAULT_SPECIALTY);
        yoe = new Yoe(DEFAULT_YOE);
        tags = new HashSet<>();
        patients = new HashSet<>();
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code doctorToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        name = doctorToCopy.getName();
        phone = doctorToCopy.getPhone();
        email = doctorToCopy.getEmail();
        specialty = doctorToCopy.getSpecialty();
        yoe = doctorToCopy.getYoe();
        tags = new HashSet<>(doctorToCopy.getTags());
        patients = new HashSet<>(doctorToCopy.getPatients());
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Doctor} that we are building.
     */
    public DoctorBuilder withTags(String ... tags) {
        this.tags = LoaderUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Specialty} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withSpecialty(String specialty) {
        this.specialty = new Specialty(specialty);
        return this;
    }

    /**
     * Sets the {@code Yoe} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withYoe(String yoe) {
        this.yoe = new Yoe(yoe);
        return this;
    }

    /**
     * Adds the list of {@code Patient} to the patient list
     * of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withPatients(Patient ... patients) {
        this.patients = LoaderUtil.getPatientSet(patients);
        return this;
    }

    public Doctor build() {
        return new Doctor(name, phone, email, specialty, yoe, tags, patients);
    }

}

