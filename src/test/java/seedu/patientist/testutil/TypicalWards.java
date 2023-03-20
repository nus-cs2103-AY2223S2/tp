package seedu.patientist.testutil;

import static seedu.patientist.testutil.TypicalPatients.ADAM;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalPatients.BOB;
import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;
import static seedu.patientist.testutil.TypicalStaff.DACIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.ward.Ward;

/**
 * A utility class containing a list of {@code Ward} objects to be used in tests.
 */
public class TypicalWards {
    public static final Ward BLOCK_A_WARD_1 = new WardBuilder().withName("Block A Ward 1")
            .withPatient(AMY).withPatient(CHARLIE).withStaff(CHARLES).build();

    public static final Ward BLOCK_A_WARD_2 = new WardBuilder().withName("Block A Ward 2")
            .withPatient(ADAM).withPatient(BOB).withStaff(DACIA).build();

    private TypicalWards() {}

    public static List<Ward> getTypicalWards() {
        return new ArrayList<>(Arrays.asList(BLOCK_A_WARD_1, BLOCK_A_WARD_2));
    }

    /**
     * Creates a generic Patientist using the typical wards.
     */
    public static Patientist getTypicalPatientist() {
        Patientist pt = new Patientist();
        pt.addWard(BLOCK_A_WARD_1);
        pt.addWard(BLOCK_A_WARD_2);
        return pt;
    }
}
