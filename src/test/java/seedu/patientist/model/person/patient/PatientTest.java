package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.testutil.TypicalPatients.ADAM;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.tag.RoleTag;
import seedu.patientist.testutil.PatientBuilder;
import seedu.patientist.testutil.TypicalPatients;

class PatientTest {
    @Test
    public void isSamePerson_differentPatient_false() {
        assertFalse(ADAM.isSamePerson(BOB));
    }

    @Test
    public void isSamePerson_samePatient_true() {
        // same object -> return true
        assertTrue(ADAM.isSamePerson(ADAM));
    }

    @Test
    public void isSamePerson_compareToNull_false() {
        // null -> returns false
        assertFalse(ADAM.isSamePerson(null));
    }

    @Test
    public void isSamePerson_sameIdDifferentAttr_true() {
        // same id, different attributes -> returns true
        Patient other = new PatientBuilder()
                .withName("different name")
                .withPhone("98843743")
                .withEmail("example@email.com")
                .withAddress("sampleaddress")
                .withIdNumber(ADAM.getIdNumber().toString())
                .build();
        assertTrue(other.isSamePerson(ADAM));
    }

    @Test
    public void isSamePerson_sameIdDifferentCase_true() {
        // same id but input in lowercase -> returns true
        Patient first = new PatientBuilder().withIdNumber("a12345b").build();
        Patient second = new PatientBuilder().withIdNumber("A12345B").build();
        assertTrue(first.isSamePerson(second));
    }

    @Test
    public void isSamePerson_sameAttrDifferentId_false() {
        Patient editedAdam = new PatientBuilder(ADAM).withIdNumber("D902838J").build();
        assertFalse(ADAM.isSamePerson(editedAdam));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(TypicalPatients.AMY).build();
        assertTrue(TypicalPatients.AMY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalPatients.AMY.equals(TypicalPatients.AMY));

        // null -> returns false
        assertFalse(TypicalPatients.AMY.equals(null));

        // different type -> returns false
        assertFalse(TypicalPatients.AMY.equals(5));

        // different person -> returns false
        assertFalse(TypicalPatients.AMY.equals(TypicalPatients.BOB));

        // different name -> returns false
        Patient editedAmy = new PatientBuilder(TypicalPatients.AMY).withName(VALID_NAME_BOB).build();
        assertFalse(TypicalPatients.AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new PatientBuilder(TypicalPatients.AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(TypicalPatients.AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new PatientBuilder(TypicalPatients.AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(TypicalPatients.AMY.equals(editedAmy));

        // different id
        editedAmy = new PatientBuilder(TypicalPatients.AMY).withIdNumber("A757575757B").build();
        assertFalse(TypicalPatients.AMY.equals(editedAmy));

        // different patientist -> returns false
        editedAmy = new PatientBuilder(TypicalPatients.AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(TypicalPatients.AMY.equals(editedAmy));
    }

    @Test
    public void getRoleTag_returnsCorrectTag() {
        RoleTag patientTag = new RoleTag("Patient");
        assertEquals(patientTag, ADAM.getRoleTag());
        assertEquals(patientTag, AMY.getRoleTag());
    }
}
