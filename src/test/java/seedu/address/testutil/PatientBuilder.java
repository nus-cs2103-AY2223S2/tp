package seedu.address.testutil;

import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Status;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NRIC = "T1234567A";
    public static final String DEFAULT_NAME = "Alex Smith";
    public static final String DEFAULT_STATUS = "GRAY";

    private Nric nric;
    private Name name;
    private Status status;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        nric = patientToCopy.getNric();
        name = patientToCopy.getName();
        status = patientToCopy.getStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Patient} that we are building.
     */
    public PatientBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    public Patient build() {
        return new Patient(nric, name, status);
    }
}
