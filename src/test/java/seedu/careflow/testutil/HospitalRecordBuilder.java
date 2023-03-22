package seedu.careflow.testutil;

import seedu.careflow.model.HospitalRecord;
import seedu.careflow.model.hospital.Hospital;

/**
 * A utility class to help with building HospitalRecord objects.
 * Example usage: <br>
 *     {@code HospitalRecord hr = new HospitalRecordBuilder().withHospital("Changi Hospital", "NUH").build();}
 */
public class HospitalRecordBuilder {
    private HospitalRecord hospitalRecord;

    public HospitalRecordBuilder() {
        hospitalRecord = new HospitalRecord();
    }

    public HospitalRecordBuilder(HospitalRecord hospitalRecord) {
        this.hospitalRecord = hospitalRecord;
    }

    /**
     * Adds a new {@code Patient} to the {@code CareFlow} that we are building.
     */
    public HospitalRecordBuilder withHospital(Hospital hospital) {
        hospitalRecord.addHospital(hospital);
        return this;
    }

    public HospitalRecord build() {
        return hospitalRecord;
    }

}
