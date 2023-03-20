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

    public static final Hospital KK_WOMEN_AND_CHILDREN = new Hospital("KK Women's and Children's Hospital",
            "+65 62255554");
    public static final Hospital CHANGI_GENERAL = new Hospital("Changi General Hospital",
            "+65 67888833");
    public static final Hospital KHOO_TECK_PHAT = new Hospital("Khoo Teck Puat Hospital",
            "+65 65558000");
    public static final Hospital TAN_TOCK_SENG = new Hospital("Tan Tock Seng Hospital",
            "+65 62566011");

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
        return new ArrayList<>(Arrays.asList(KK_WOMEN_AND_CHILDREN, CHANGI_GENERAL,
                KHOO_TECK_PHAT, TAN_TOCK_SENG));
    }
}
