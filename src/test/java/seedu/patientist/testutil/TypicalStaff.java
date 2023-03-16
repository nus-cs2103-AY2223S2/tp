package seedu.patientist.testutil;

import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.staff.Staff;

/**
 * A utility class containing a list of {@code Staff} objects to be used in tests.
 */
public class TypicalStaff {
    public static final Staff AMY = new StaffBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags("Block3WardD")
            .withIdNumber(VALID_PID_AMY).build();

    public static final Staff BOB = new StaffBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB)
            .withPhone(VALID_PHONE_BOB).withIdNumber(VALID_PID_BOB)
            .withTags("Block2WardC").build();

    public static final Staff CHARLES = new StaffBuilder().withName("Charles Lee")
            .withAddress("123, abc, #08-111").withEmail("leecharles@example.com")
            .withPhone("2136784").withIdNumber("G487659645D")
            .withTags("Block2WardA").build();

    public static final Staff DACIA = new StaffBuilder().withName("Dacia Chin")
            .withAddress("970, Hindhede St, #07-27").withEmail("daciachin@example.com")
            .withPhone("96128393").withIdNumber("L73825263J")
            .withTags("Block1WardA").build();

    private TypicalStaff() {} // prevents instantiation

    /**
     * Returns an {@code Patientist} with all the typical persons.
     */
    public static Patientist getTypicalPatientist() {
        Patientist pt = new Patientist();
        for (Staff staff : getTypicalStaff()) {
            //pt.addPerson(staff); todo
        }
        return pt;
    }

    public static List<Staff> getTypicalStaff() {
        return new ArrayList<>(Arrays.asList(AMY, BOB, CHARLES, DACIA));
    }
}
