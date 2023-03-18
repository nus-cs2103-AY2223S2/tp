package seedu.address.testutil;


import java.io.StringBufferInputStream;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building {@code Patient} objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Bobby Bob";
    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_EMAIL = "boby@gmail.org";
    public static final String DEFAULT_HEIGHT = "1.7";
    public static final String DEFAULT_WEIGHT = "65";
    public static final String DEFAULT_DIAGNOSIS = "Asthma";
    public static final String DEFAULT_STATUS = Status.getDummyValidStatus();
    public static final String DEFAULT_REMARK = "Prescribed 2 doses of budesonide, " +
            "to be taken morning and night daily.";

    private Name name;
    private Phone phone;
    private Email email;
    private Height height;
    private Weight weight;
    private Diagnosis diagnosis;
    private Status status;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        diagnosis = new Diagnosis(DEFAULT_DIAGNOSIS);
        status = new Status(DEFAULT_STATUS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        height = patientToCopy.getHeight();
        weight = patientToCopy.getWeight();
        diagnosis = patientToCopy.getDiagnosis();
        status = patientToCopy.getStatus();
        remark = patientToCopy.getRemark();
        tags = new HashSet<>(patientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Height} of the {@code Patient} that we are building.
     */
    public PatientBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Patient} that we are building.
     */
    public PatientBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Diagnosis} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = new Diagnosis(diagnosis);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Patient} that we are building.
     */
    public PatientBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Patient} that we are building.
     */
    public PatientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, height,
                weight, diagnosis, status, remark, tags);
    }

}

