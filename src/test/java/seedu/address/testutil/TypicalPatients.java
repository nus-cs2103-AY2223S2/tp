package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Status;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ZAYDEN = new PatientBuilder()
            .withName("Zayden Lim")
            .withEmail("zayden@gmail.com")
            .withPhone("23456978")
            .withTags("teenager")
            .withHeight("1.6")
            .withWeight("64")
            .withDiagnosis("Asthma")
            .withStatus(Status.getDummyValidStatus(0))
            .withRemark("Compliant")
            .build();

    public static final Patient YANNIE = new PatientBuilder()
            .withName("Yannie Teo")
            .withEmail("yannie@hotmail.com")
            .withPhone("23456234")
            .withTags("high-priority")
            .withHeight("1.57")
            .withWeight("56")
            .withDiagnosis("Cancer")
            .withStatus(Status.getDummyValidStatus(1))
            .withRemark("Special Attention Needed")
            .build();

    public static final Patient XANNY = new PatientBuilder()
            .withName("Xanny Peters")
            .withEmail("xannypeters@outlook.com")
            .withPhone("25466234")
            .withTags("low-priority")
            .withHeight("1.8")
            .withWeight("76")
            .withDiagnosis("Pneumonia")
            .withStatus(Status.getDummyValidStatus(3))
            .withRemark("Needs to be warded")
            .build();

    public static final Patient WALTER = new PatientBuilder()
            .withName("Walter White")
            .withEmail("breakingbad@outlook.com")
            .withPhone("18001800")
            .withTags("severe-priority")
            .withHeight("1.9")
            .withWeight("68")
            .withDiagnosis("Eating Disorder")
            .withStatus(Status.getDummyValidStatus(5))
            .withRemark("Check back regarding meal plan")
            .build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ZAYDEN));
    }
}
