package seedu.patientist.testutil;

import java.util.Arrays;
import java.util.HashSet;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.tag.Tag;
import seedu.patientist.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects
 */
public class PatientBuilder extends PersonBuilder {

    public static final String DEFAULT_STATUS = "Doing good";
    public static final HashSet<Tag> DEFAULT_TAGS = new HashSet<>(Arrays.asList(new Tag("Patient")));

    private PatientStatusDetails status;

    /**
     * Creates a Patient from default details, with defaults specified here and in PersonBuilder
     */
    public PatientBuilder() {
        super();
        this.status = new PatientStatusDetails(DEFAULT_STATUS);
        this.tags = DEFAULT_TAGS;
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        idNumber = patientToCopy.getIdNumber();
        status = patientToCopy.getPatientStatusDetails();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        tags = new HashSet<>(patientToCopy.getTags());
    }
    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Patient} that we are building.
     */
    public PatientBuilder withStatus(String status) {
        this.status = new PatientStatusDetails(status);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Patient} that we are building.
     */
    @Override
    public PatientBuilder withIdNumber(String id) {
        this.idNumber = new IdNumber(id);
        return this;
    }

    @Override
    public Patient build() {
        return new Patient(idNumber, name, phone, email, address, status, tags);
    }

}
