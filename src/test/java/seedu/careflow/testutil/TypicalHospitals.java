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
            new HospitalBuilder().withHospitalName("Changi General Hospital").withHotline("+65 6788 8833").build();
    public static final Hospital SINGAPORE_GENERAL_HOSPITAL =
            new HospitalBuilder().withHospitalName("Singapore General Hospital").withHotline("+65 6222 3322").build();
    public static final Hospital TAN_TOCK_SENG_HOSPITAL =
            new HospitalBuilder().withHospitalName("Tan Tock Seng Hospital").withHotline("+65 6256 6011").build();
    public static final Hospital ALEXANDRA_HOSPITAL =
            new HospitalBuilder().withHospitalName("Alexandra Hospital").withHotline("+65 6472 0000").build();

    public static final Hospital NATIONAL_UNIVERSITY_HOSPITAL =
            new HospitalBuilder().withHospitalName("National University Hospital").withHotline("+65 6779 5555").build();

    public static final Hospital CRAWFURD_HOSPITAL =
            new HospitalBuilder().withHospitalName("Crawfurd Hospital").withHotline("+65-6933-3723").build();

    public static final String VALID_TAN_TOCK_SENG_HOSPITAL_NAME = "Tan Tock Seng Hospital";
    public static final String VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE = "+65 6256 6011";

    private TypicalHospitals() {} // prevents instantiation

    /**
     * Returns an {@code Careflow} with all the typical hospital.
     */
    public static HospitalRecord getTypicalHospitalRecord() {
        HospitalRecord pr = new HospitalRecord();
        for (Hospital hospital : getTypicalHospitals()) {
            pr.addHospital(hospital);
        }
        return pr;
    }

    public static List<Hospital> getTypicalHospitals() {
        return new ArrayList<>(Arrays.asList(CHANGI_GENERAL_HOSPITAL, SINGAPORE_GENERAL_HOSPITAL,
                TAN_TOCK_SENG_HOSPITAL, ALEXANDRA_HOSPITAL));
    }
}
