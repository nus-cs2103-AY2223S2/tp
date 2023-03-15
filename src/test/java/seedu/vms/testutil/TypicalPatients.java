package seedu.vms.testutil;

import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder()
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withDob("2020-07-09")
            .withBloodType("A+")
            .withAllergies("seafood", "gluten")
            .build();
    public static final Patient BENSON = new PatientBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withDob("2011-10-11")
            .withBloodType("A-")
            .withAllergies("seafood")
            .build();
    public static final Patient CARL = new PatientBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withDob("1999-12-30")
            .withBloodType("B+")
            .withAllergies("seafood", "gluten")
            .withVaccines("Moderna", "Pfizer")
            .build();
    public static final Patient DANIEL = new PatientBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withDob("1994-02-27")
            .withBloodType("B-")
            .withAllergies("seafood")
            .withVaccines("Moderna")
            .build();
    public static final Patient ELLE = new PatientBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withDob("1989-09-19")
            .withBloodType("AB+")
            .withVaccines("Moderna", "Pfizer")
            .build();
    public static final Patient FIONA = new PatientBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withDob("1988-04-30")
            .withBloodType("AB-")
            .withVaccines("Moderna")
            .build();
    public static final Patient GEORGE = new PatientBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withDob("1981-09-20")
            .withBloodType("O+")
            .build();

    // Manually added
    public static final Patient HOON = new PatientBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withDob("1991-02-20")
            .withBloodType("O-")
            .build();
    public static final Patient IDA = new PatientBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withDob("1995-03-23")
            .withBloodType("A+")
            .build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withDob(VALID_DOB_AMY)
            .withBloodType(VALID_BLOODTYPE_AMY)
            .build();
    public static final Patient BOB = new PatientBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withDob(VALID_DOB_BOB)
            .withBloodType(VALID_BLOODTYPE_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code PatientManager} with all the typical patients.
     */
    public static PatientManager getTypicalPatientManager() {
        PatientManager pm = new PatientManager();
        for (Patient patient : getTypicalPatients()) {
            pm.add(patient);
        }
        return pm;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
