package seedu.careflow.testutil;

import seedu.careflow.model.HospitalRecord;
import seedu.careflow.model.hospital.Hospital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Hospital} objects to be used in tests.
 */
public class TypicalHospitals {

    public static final Hospital KK_Women_and_Children_Hospital = new Hospital("KK Women's and Children's Hospital", "+65 62255554");
    public static final Hospital Changi_General_Hospital = new Hospital("Changi General Hospital", "+65 67888833");
    public static final Hospital Khoo_Teck_Puat_Hospital = new Hospital("Khoo Teck Puat Hospital", "+65 65558000");
    public static final Hospital Tan_Tock_Seng_Hospital = new Hospital("Tan Tock Seng Hospital", "+65 62566011");

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalHospitals() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static HospitalRecord getTypicalHospitalRecord() {
        HospitalRecord hr = new HospitalRecord();
        for (Hospital hospital : getTypicalHospital()) {
            hr.addHospital(hospital);
        }
        return hr;
    }

    public static List<Hospital> getTypicalHospital() {
        return new ArrayList<>(Arrays.asList(KK_Women_and_Children_Hospital, Changi_General_Hospital,
                Khoo_Teck_Puat_Hospital, Tan_Tock_Seng_Hospital));
    }
}
