package seedu.careflow.testutil;

import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Phone;

/**
 * A utility class to help with building Hospital objects.
 */
public class HospitalBuilder {
    public static final String DEFAULT_HOSPITAL_NAME = "National University Hospital";
    public static final String DEFAULT_HOTLINE = "67795555";

    private Name hospitalName;
    private Phone hotline;

    /**
     * Creates a {@code HospitalBuilder} with the default details.
     */
    public HospitalBuilder() {
        hospitalName = new Name(DEFAULT_HOSPITAL_NAME);
        hotline = new Phone(DEFAULT_HOTLINE);
    }

    /**
     * Initializes the HospitalBuilder with the data of {@code hospitalToCopy}.
     */
    public HospitalBuilder(Hospital hospitalToCopy) {
        hospitalName = hospitalToCopy.getHospitalName();
        hotline = hospitalToCopy.getHotline();
    }

    /**
     * Sets the {@code Name} of the {@code Hospital} that we are building.
     */
    public HospitalBuilder withHospitalName(String name) {
        this.hospitalName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Hospital} that we are building.
     */
    public HospitalBuilder withHotline(String phone) {
        this.hotline = new Phone(phone);
        return this;
    }

    public Hospital build() {
        return new Hospital(hospitalName, hotline);
    }
}
