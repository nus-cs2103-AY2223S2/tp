package seedu.patientist.model.person.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.testutil.TypicalStaff.AMY;
import static seedu.patientist.testutil.TypicalStaff.BOB;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;
import static seedu.patientist.testutil.TypicalStaff.DACIA;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.StaffBuilder;
import seedu.patientist.testutil.TypicalPatients;
import seedu.patientist.testutil.TypicalPersons;

public class StaffTest {
    @Test
    public void isSameStaff_compareToSameNamePatient_false() {
        assertFalse(AMY.isSamePerson(TypicalPatients.AMY));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(AMY.isSamePerson(AMY));

        // null -> returns false
        assertFalse(DACIA.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Staff editedCharles = new StaffBuilder(CHARLES).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags("Block7WardA").build();
        assertTrue(CHARLES.isSamePerson(editedCharles));

        // different name, all other attributes same -> returns false
        editedCharles = new StaffBuilder(CHARLES).withName(VALID_NAME_BOB).build();
        assertFalse(CHARLES.isSamePerson(editedCharles));

        // name differs in case, all other attributes same -> returns false
        Staff editedBob = new StaffBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff aliceCopy = new StaffBuilder(AMY).build();
        assertTrue(AMY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different person -> returns false
        assertFalse(AMY.equals(TypicalPersons.BOB));

        // different name -> returns false
        Staff editedAmy = new StaffBuilder(AMY).withName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new StaffBuilder(AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new StaffBuilder(AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different patientist -> returns false
        editedAmy = new StaffBuilder(AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different ward -> returns false
        editedAmy = new StaffBuilder(AMY).withTags("Block7WardA").build();
        assertFalse(AMY.equals(editedAmy));
    }
}
