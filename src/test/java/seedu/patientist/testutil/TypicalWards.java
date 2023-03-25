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

    private TypicalWards() {}

    public static List<Ward> getTypicalWards() {
        return new ArrayList<>(Arrays.asList(getBlockAWard1(), getBlockAWard2()));
    }

    public static Ward getBlockAWard1() {
        return new WardBuilder().withName("Block A Ward 1")
                .withPatient(AMY).withPatient(CHARLIE).withStaff(CHARLES).build();
    }

    public static Ward getBlockAWard2() {
        return new WardBuilder().withName("Block A Ward 2")
                .withPatient(ADAM).withPatient(BOB).withStaff(DACIA).build();
    }

    /**
     * Creates a generic Patientist using the typical wards.
     */
    public static Patientist getTypicalPatientist() {
        Patientist pt = new Patientist();
        pt.addWard(getBlockAWard1());
        pt.addWard(getBlockAWard2());
        return pt;
    }
}
