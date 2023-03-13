package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.patient.NRIC;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NRIC = "T1234567A";
    public static final String DEFAULT_NAME = "Alex Smith";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private NRIC nric;
    private Name name;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        nric = new NRIC(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code patientToCopy}.
     */
    public PersonBuilder(Patient patientToCopy) {
        nric = patientToCopy.getNric();
        name = patientToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public Patient build() {
        return new Patient(nric, name);
    }

}
