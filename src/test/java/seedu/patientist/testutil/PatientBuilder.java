package seedu.patientist.testutil;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientIdNumber;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.util.SampleDataUtil;

public class PatientBuilder extends PersonBuilder {

    public static final String DEFAULT_STATUS =
            """
            The patient is currently in stable condition.
            The patient is required to attend physiotherapy 3 times a week
            The patient has medical allergy as follows: None
            """;
    public static final String DEFAULT_ID = "A1234567890B";

    private PatientStatusDetails status;
    private PatientIdNumber id;

    public PatientBuilder() {
        super();
        this.status = new PatientStatusDetails(DEFAULT_STATUS);
        this.id = new PatientIdNumber(DEFAULT_ID);
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
    public PatientBuilder withId(String id) {
        this.id = new PatientIdNumber(id);
        return this;
    }

    @Override
    public Patient build() {
        return new Patient(id, name, phone, email, address, status, tags);
    }

}
