package seedu.medinfo.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.medinfo.testutil.TypicalPatients.ALEX;
import static seedu.medinfo.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.medinfo.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALEX.isSamePatient(ALEX));

        // null -> returns false
        assertFalse(ALEX.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedAlex = new PatientBuilder(ALEX).withStatus(VALID_STATUS_AMY).build();
        assertTrue(ALEX.isSamePatient(editedAlex));

        // different name, all other attributes same -> returns false
        editedAlex = new PatientBuilder(ALEX).withName(VALID_NAME_BOB).build();
        assertFalse(ALEX.isSamePatient(editedAlex));

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
        Patient aliceCopy = new PatientBuilder(ALEX).build();
        assertTrue(ALEX.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALEX.equals(ALEX));

        // null -> returns false
        assertFalse(ALEX.equals(null));

        // different type -> returns false
        assertFalse(ALEX.equals(5));

        // different patient -> returns false
        assertFalse(ALEX.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALEX).withName(VALID_NAME_BOB).build();
        assertFalse(ALEX.equals(editedAlice));

        // different nric -> returns false
        editedAlice = new PatientBuilder(ALEX).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALEX.equals(editedAlice));
    }
}
