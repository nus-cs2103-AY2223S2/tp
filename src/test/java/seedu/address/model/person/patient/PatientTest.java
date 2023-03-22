package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_YANNIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.YANNIE;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getTags().remove(0));
    }

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ZAYDEN.isSamePatient(ZAYDEN));

        // null -> returns false
        assertFalse(ZAYDEN.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedZayden = new PatientBuilder(ZAYDEN).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB).withDiagnosis(VALID_DIAGNOSIS_BOB)
                .withStatus(VALID_STATUS_BOB).withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ZAYDEN.isSamePatient(editedZayden));

        // different name, all other attributes same -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withName(VALID_NAME_BOB).build();
        assertFalse(ZAYDEN.isSamePatient(editedZayden));

        // name differs in case, all other attributes same -> returns false
        Patient editedYannie = new PatientBuilder(YANNIE).withName(VALID_NAME_YANNIE.toLowerCase()).build();
        assertFalse(YANNIE.isSamePatient(editedYannie));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedYannie = new PatientBuilder(YANNIE).withName(nameWithTrailingSpaces).build();
        assertFalse(YANNIE.isSamePatient(editedYannie));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient zaydenCopy = new PatientBuilder(ZAYDEN).build();
        assertTrue(ZAYDEN.equals(zaydenCopy));

        // same object -> returns true
        assertTrue(ZAYDEN.equals(ZAYDEN));

        // null -> returns false
        assertFalse(ZAYDEN.equals(null));

        // different type -> returns false
        assertFalse(ZAYDEN.equals(5));

        // different person -> returns false
        assertFalse(ZAYDEN.equals(YANNIE));

        // different name -> returns false
        Patient editedZayden = new PatientBuilder(ZAYDEN).withName(VALID_NAME_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different phone -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different email -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different height -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different weight -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different diagnosis -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withDiagnosis(VALID_DIAGNOSIS_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different status -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withStatus(VALID_STATUS_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different remark -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withRemark(VALID_REMARK_BOB).build();
        assertFalse(ZAYDEN.equals(editedZayden));

        // different tags -> returns false
        editedZayden = new PatientBuilder(ZAYDEN).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ZAYDEN.equals(editedZayden));
    }
}
