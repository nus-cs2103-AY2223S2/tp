package seedu.patientist.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalStaff.AMY;
import static seedu.patientist.testutil.TypicalStaff.BOB;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.StaffBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new StaffBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(AMY.isSamePerson(AMY));

        // null -> returns false
        assertFalse(AMY.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAmy = new StaffBuilder(AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).withIdNumber(VALID_PID_BOB).build();
        assertFalse(AMY.isSamePerson(editedAmy));

        // different name, all other attributes same -> returns true
        editedAmy = new StaffBuilder(AMY).withName(VALID_NAME_BOB).build();
        assertTrue(AMY.isSamePerson(editedAmy));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new StaffBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person amyCopy = new StaffBuilder(AMY).build();
        assertTrue(AMY.equals(amyCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different person -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Person editedAlice = new StaffBuilder(AMY).withName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StaffBuilder(AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StaffBuilder(AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different patientist -> returns false
        editedAlice = new StaffBuilder(AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StaffBuilder(AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(AMY.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StaffBuilder(AMY).withIdNumber(VALID_PID_BOB).build();
        assertFalse(AMY.equals(editedAlice));
    }
}
