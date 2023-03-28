package seedu.vms.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_GLUTEN;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPatients.ALICE;
import static seedu.vms.testutil.TypicalPatients.BOB;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.vms.model.GroupName;
import seedu.vms.testutil.PatientBuilder;
import seedu.vms.testutil.TestUtil;

public class PatientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getAllergy().remove(0));
    }

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withDob(VALID_DOB_BOB)
                .withBloodType(VALID_BLOODTYPE_BOB).withAllergies(VALID_ALLERGY_GLUTEN).build();
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

        // different patient -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different dob -> returns false
        editedAlice = new PatientBuilder(ALICE).withDob(VALID_DOB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different bloodType -> returns false
        editedAlice = new PatientBuilder(ALICE).withBloodType(VALID_BLOODTYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different allergies -> returns false
        editedAlice = new PatientBuilder(ALICE).withAllergies(VALID_ALLERGY_GLUTEN).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void valueAboveLimitThrowException() {
        HashSet<GroupName> allergyOverLimitSet = TestUtil.generateGroupSet(Patient.LIMIT_ALLERGIES + 1);

        assertThrows(IllegalArgumentException.class,
                () -> new PatientBuilder().withName("Alice").withAllergies(TestUtil.toStringArr(allergyOverLimitSet))
                        .build());

        HashSet<GroupName> vaccineOverLimitSet = TestUtil.generateGroupSet(Patient.LIMIT_VACCINES + 1);
        assertThrows(IllegalArgumentException.class,
                () -> new PatientBuilder().withName("Bob").withVaccines(TestUtil.toStringArr(vaccineOverLimitSet))
                        .build());
    }

}
