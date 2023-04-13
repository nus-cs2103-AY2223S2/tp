package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NRIC = "S1234967G";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ROLE_PATIENT = "Patient";

    private Name name;
    private Phone phone;
    private Email email;
    private Nric nric;
    private Address address;
    private Set<Prescription> prescriptions;
    private Set<Tag> tags;
    private ArrayList<Appointment> appointments;
    private Role role;
    private boolean isDoctor;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nric = new Nric(DEFAULT_NRIC);
        address = new Address(DEFAULT_ADDRESS);
        prescriptions = new HashSet<>();
        tags = new HashSet<>();
        appointments = new ArrayList<>();
        role = new Role(DEFAULT_ROLE_PATIENT);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code role}.
     */
    public PersonBuilder(String role) {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nric = new Nric(DEFAULT_NRIC);
        address = new Address(DEFAULT_ADDRESS);
        prescriptions = new HashSet<>();
        tags = new HashSet<>();
        appointments = new ArrayList<>();
        this.role = new Role(role);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        nric = personToCopy.getNric();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        appointments = new ArrayList<>(personToCopy.getPatientAppointments());
        role = personToCopy.getRole();

        if (personToCopy instanceof Patient) {
            Patient patientToCopy = (Patient) personToCopy;
            prescriptions = new HashSet<>(patientToCopy.getPrescriptions());
        }
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Medication} of the {@code Person} that we are building.
     */
    public PersonBuilder withPrescriptions(String ... prescriptions) {
        this.prescriptions = SampleDataUtil.getPrescriptionSet(prescriptions);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointment(String ... appointment) {
        this.appointments.add(SampleDataUtil.getAppointment(appointment));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Returns {@code Doctor} or {@code Patient} with all the details defined by the with functions.
     * Undefined attributes will have default values
     */
    public Person build() {
        if (isDoctor) {
            return buildDoctor();
        } else {
            return buildPatient();
        }
    }

    public Patient buildPatient() {
        return new Patient(name, phone, email, nric, address, prescriptions, tags, appointments, role);
    }

    public Doctor buildDoctor() {
        return new Doctor(name, phone, email, nric, address, tags, appointments, role);
    }

}
