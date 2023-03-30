package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_BOB;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.AMY;
import static seedu.careflow.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void constructor_nullValueInRequiredField_throwsNullPointerException() {
        PatientBuilder alice = new PatientBuilder(ALICE);
        assertThrows(NullPointerException.class, () -> alice.withName(null).build());
        assertThrows(NullPointerException.class, () -> alice.withEmail(null).build());
        assertThrows(NullPointerException.class, () -> alice.withAddress(null).build());
        assertThrows(NullPointerException.class, () -> alice.withPhone(null).build());
        assertThrows(NullPointerException.class, () -> alice.withDob(null).build());
        assertThrows(NullPointerException.class, () -> alice.withGender(null).build());
        assertThrows(NullPointerException.class, () -> alice.withIc(null).build());
    }

    @Test
    public void constructor_nullValueInOptionalField_notThrowsNullPointerException() {
        PatientBuilder editedAlice = new PatientBuilder(ALICE);
        assertDoesNotThrow(() -> editedAlice.withDrugAllergy(null).build());
        assertDoesNotThrow(() -> editedAlice.withEmergencyContact(null).build());
    }

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        // same name, one attributes different -> returns true
        Patient editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same name, all others required attributes different -> returns true
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withDob(VALID_BIRTHDATE_BOB).withGender(VALID_GENDER_BOB)
                .withIc(VALID_IC_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same name, all others required and optional attributes different -> returns true
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withDob(VALID_BIRTHDATE_BOB).withGender(VALID_GENDER_BOB)
                .withIc(VALID_IC_BOB).withDrugAllergy(VALID_DRUG_ALLERGY_BOB)
                .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePatient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Patient editedBob = new PatientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePatient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PatientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePatient(editedBob));
    }

    @Test
    public void isSameIc() {
        // null -> returns false
        assertFalse(ALICE.isSameIc(null));

        // same ic, all others attributes same(except for name) -> returns true
        Patient editedPatient = new PatientBuilder(AMY)
                .withName(VALID_NAME_BOB).withIc(VALID_IC_AMY.toUpperCase()).build();
        assertTrue(AMY.isSameIc(editedPatient));

        // same ic, all other attributes different -> returns true
        editedPatient = new PatientBuilder(BOB).withIc(VALID_IC_AMY.toUpperCase()).build();
        assertTrue(AMY.isSameIc(editedPatient));

        // same ic but differs in case -> return true
        Patient amyWithCapitalIC = new PatientBuilder().withIc(VALID_IC_AMY.toUpperCase()).build();
        editedPatient = new PatientBuilder(BOB).withIc(VALID_IC_AMY.toLowerCase()).build();
        assertTrue(amyWithCapitalIC.isSameIc(editedPatient));

        // different ic, all others attributes same -> return false
        editedPatient = new PatientBuilder(AMY).withIc(VALID_IC_BOB).build();
        assertFalse(AMY.isSameIc(editedPatient));

        // different ic, all others attributes different -> return false
        editedPatient = new PatientBuilder(BOB).build();
        assertFalse(AMY.isSameIc(editedPatient));
    }


    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PatientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different dob -> returns false
        editedAlice = new PatientBuilder(ALICE).withDob(VALID_BIRTHDATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different gender -> returns false
        editedAlice = new PatientBuilder(ALICE).withGender(VALID_GENDER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ic -> returns false
        editedAlice = new PatientBuilder(ALICE).withIc(VALID_IC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different drug allergy -> returns false
        // drug allergy with new values
        editedAlice = new PatientBuilder(ALICE).withDrugAllergy(VALID_DRUG_ALLERGY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
        // drug allergy with null
        editedAlice = new PatientBuilder(ALICE).withDrugAllergy(null).build();
        assertFalse(ALICE.equals(editedAlice));

        // different emergency contact -> returns false
        // emergency contact with new values
        editedAlice = new PatientBuilder(ALICE).withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
        // emergency contact with null
        editedAlice = new PatientBuilder(ALICE).withEmergencyContact(null).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
