package seedu.careflow.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.careflow.model.HospitalRecord;
import seedu.careflow.model.hospital.Hospital;

/**
 * A utility class containing a list of {@code Hospital} objects to be used in tests.
 */
public class TypicalHospitals {
    public static final Hospital CHANGI_GENERAL_HOSPITAL =
            new HospitalBuilder().withHospitalName("Changi General Hospital").withHotline("67888833").build();
    public static final Hospital SINGAPORE_GENERAL_HOSPITAL =
            new HospitalBuilder().withHospitalName("Singapore General Hospital").withHotline("62223322").build();
    public static final Hospital TAN_TOCK_SENG_HOSPITAL =
            new HospitalBuilder().withHospitalName("Tan Tock Seng Hospital").withHotline("62566011").build();
    public static final Hospital ALEXANDRA_HOSPITAL =
            new HospitalBuilder().withHospitalName("Alexandra Hospital").withHotline("64720000").build();

    public static final Hospital NATIONAL_UNIVERSITY_HOSPITAL =
            new HospitalBuilder().withHospitalName("National University Hospital").withHotline("67795555").build();

    public static final Hospital CRAWFURD_HOSPITAL =
            new HospitalBuilder().withHospitalName("Crawfurd Hospital").withHotline("69333723").build();

    public static final String VALID_TAN_TOCK_SENG_HOSPITAL_NAME = "Tan Tock Seng Hospital";
    public static final String VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE = "62566011";

    private TypicalHospitals() {} // prevents instantiation

    /**
     * Returns an {@code Careflow} with all the typical hospital.
     */
    public static HospitalRecord getTypicalHospitalRecord() {
        HospitalRecord hr = new HospitalRecord();
        for (Hospital hospital : getTypicalHospitals()) {
            hr.addHospital(hospital);
        }
        return hr;
    }

    public static List<Hospital> getTypicalHospitals() {
        return new ArrayList<>(Arrays.asList(CHANGI_GENERAL_HOSPITAL, SINGAPORE_GENERAL_HOSPITAL,
                TAN_TOCK_SENG_HOSPITAL, ALEXANDRA_HOSPITAL));
    }
}
