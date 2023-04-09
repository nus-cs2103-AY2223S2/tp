package seedu.medinfo.testutil;

import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_DISCHARGE_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_DISCHARGE_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_WARD_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_WARD_BOB;
import static seedu.medinfo.model.ward.Ward.wardWithName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.WardName;

/**
 * A utility class containing a list of {@code Patient} objects to be used in
 * tests.
 */
public class TypicalPatients {

    // Patients
    public static final Patient ALEX = new PatientBuilder().withNric("S1000007A")
            .withName("Alex Smith").withStatus("GRAY").withWard(new WardName("Waiting Room"))
            .withDischarge("12/02/2023 1400").build();
    public static final Patient BENSON = new PatientBuilder().withNric("S7654321F")
            .withName("Benson Tillman").withStatus("GRAY").withWard(new WardName("Waiting Room"))
            .withDischarge("12/02/2023 1500").build();
    public static final Patient CARL = new PatientBuilder().withNric("S2468024G")
            .withName("Carl Leigh").withStatus("GREEN").withWard(new WardName("Class A"))
            .withDischarge("12/02/2023 1600").build();
    public static final Patient DANIEL = new PatientBuilder().withNric("S1244567A")
            .withName("Daniel Wellington").withStatus("YELLOW").withWard(new WardName("Class B"))
            .withDischarge("13/02/2023 1400").build();
    public static final Patient ELLE = new PatientBuilder().withNric("S1235567A")
            .withName("Elle Schmidt").withStatus("GREEN").withWard(new WardName("Class C"))
            .withDischarge("13/02/2023 1500").build();
    public static final Patient FIONA = new PatientBuilder().withNric("S6969696B")
            .withName("Fiona Shrekt").withStatus("RED").withWard(new WardName("ICU"))
            .withDischarge("13/02/2023 1600").build();
    public static final Patient GEORGE = new PatientBuilder().withNric("S1224567A")
            .withName("George Townsend").withStatus("RED").withWard(new WardName("ICU"))
            .withDischarge("14/02/2023 1400").build();

    // Wards
    public static final Ward WR = wardWithName("Waiting Room");
    public static final Ward A = wardWithName("Class A").withCapacity(30);
    public static final Ward B = wardWithName("Class B").withCapacity(30);
    public static final Ward C = wardWithName("Class C").withCapacity(30);
    public static final Ward ICU = wardWithName("ICU").withCapacity(40);

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withNric("T2222222L")
            .withStatus("GREEN").withWard(new WardName("Class C")).withDischarge("22/02/2023 1540").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withNric("S3333333L")
            .withStatus("YELLOW").withWard(new WardName("Class B")).withDischarge("25/02/2023 1400").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY)
            .withStatus(VALID_STATUS_AMY).withWard(new WardName(VALID_WARD_AMY))
            .withDischarge(VALID_DISCHARGE_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
            .withStatus(VALID_STATUS_BOB).withWard(new WardName(VALID_WARD_BOB))
            .withDischarge(VALID_DISCHARGE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {
    } // prevents instantiation

    /**
     * Returns an {@code MedInfo} with all the typical persons.
     */
    public static MedInfo getTypicalMedInfo() {
        MedInfo ab = new MedInfo();
        try {
            for (Ward ward : getTypicalWards()) {
                ab.addWard(ward);
            }

            for (Patient patient : getTypicalPatients()) {
                ab.addPatient(patient);
            }
        } catch (CommandException e) {
            System.out.println("Caught CommandException error!!!");
        }

        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALEX, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Ward> getTypicalWards() {
        return new ArrayList<>(Arrays.asList(WR, A, B, C, ICU));
    }
}
