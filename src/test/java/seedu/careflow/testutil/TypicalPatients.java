package seedu.careflow.testutil;

import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_DRUG_ALLERGY_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMERGENCY_CONTACT_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withAddress("123, Jurong West Ave 6, #08-111").withDob("01-01-1999")
            .withGender("female").withIc("T1128899I").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withAddress("311, Clementi Ave 2, #02-25").withDob("03-06-2000")
            .withGender("male").withIc("Y7778899K").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withDob("08-08-1998")
            .withGender("male").withIc("P0098811H").withDrugAllergy("Aspirin")
            .build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withDob("03-05-1971")
            .withGender("male").withIc("B9965511S").withDrugAllergy("Anticonvulsants").withEmergencyContact("83754333")
            .build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withDob("09-04-1961")
            .withGender("female").withIc("I1133113S").withDrugAllergy("Local anesthetics")
            .withEmergencyContact("81756633").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withDob("03-12-1981")
            .withGender("female").withIc("W9164411S").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withDob("01-08-1991")
            .withGender("male").withIc("B9532511S").withEmergencyContact("83704433")
            .build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withIc("P1292372A").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withIc("P1234214A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withDob(VALID_BIRTHDATE_AMY)
            .withGender(VALID_GENDER_AMY).withIc(VALID_IC_AMY).withDrugAllergy(VALID_DRUG_ALLERGY_AMY)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withDob(VALID_BIRTHDATE_BOB)
            .withGender(VALID_GENDER_BOB).withIc(VALID_IC_BOB).withDrugAllergy(VALID_DRUG_ALLERGY_BOB)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code Careflow} with all the typical patients.
     */
    public static PatientRecord getTypicalPatientRecord() {
        PatientRecord pr = new PatientRecord();
        for (Patient patient : getTypicalPatients()) {
            pr.addPatient(patient);
        }
        return pr;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
