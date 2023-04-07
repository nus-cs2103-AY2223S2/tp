package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
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
            .withHeight("1.60")
            .withWeight("64")
            .withDiagnosis("Asthma")
            .withStatus(Status.getDummyValidStatus(0))
            .withRemark("Compliant")
            .build();

    public static final Patient YANNIE = new PatientBuilder()
            .withName("Yannie Teo")
            .withEmail("yannie@hotmail.com")
            .withPhone("23456234")
            .withTags("highPriority")
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
            .withTags("lowPriority")
            .withHeight("1.82")
            .withWeight("76")
            .withDiagnosis("Pneumonia")
            .withStatus(Status.getDummyValidStatus(3))
            .withRemark("Needs to be warded")
            .build();

    public static final Patient WALTER = new PatientBuilder()
            .withName("Walter White")
            .withEmail("breakingbad@outlook.com")
            .withPhone("18001800")
            .withTags("severePriority")
            .withHeight("1.91")
            .withWeight("68")
            .withDiagnosis("Eating Disorder")
            .withStatus(Status.getDummyValidStatus(5))
            .withRemark("Check back regarding meal plan")
            .build();

    // Do not assign this patient to any doctors in test scenarios
    public static final Patient UNASSIGNED = new PatientBuilder()
            .withName("Rob Smith")
            .withEmail("robbie@gmail.com")
            .withPhone("12346666")
            .withHeight("1.74")
            .withWeight("63.4")
            .withDiagnosis("None")
            .withStatus("Outpatient")
            .withRemark("Non compliant")
            .build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
            .withDiagnosis(VALID_DIAGNOSIS_BOB).withStatus(VALID_STATUS_BOB).withRemark(VALID_REMARK_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalPatientsOnlyAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    /**
     * Returns a list of typical patients.
     *
     * @return a list of typical patients.
     */
    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(
                new PatientBuilder(ZAYDEN).build(),
                new PatientBuilder(YANNIE).build(),
                new PatientBuilder(XANNY).build(),
                new PatientBuilder(WALTER).build()));
    }

    /**
     * Returns a patient that is not assigned to any doctor
     * in test scenarios.
     *
     * @return Patient an unassigned patient.
     */
    public static Patient getUnassignedPatient() {
        return UNASSIGNED;
    }
}
