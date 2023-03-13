package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.ALICE;
import static seedu.address.testutil.TypicalDoctors.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Doctor doctor = new DoctorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> doctor.getTags().remove(0));
    }

    @Test
    public void isSameDoctor() {
        // same object -> returns true
        assertTrue(ALICE.isSameDoctor(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameDoctor(null));

        // same name, all other attributes different -> returns true
        Doctor editedAlice = new DoctorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withSpecialty(VALID_SPECIALTY_BOB).withYoe(VALID_YOE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameDoctor(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameDoctor(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Doctor editedBenson = new DoctorBuilder(BENSON).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BENSON.isSameDoctor(editedBenson));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBenson = new DoctorBuilder(BENSON).withName(nameWithTrailingSpaces).build();
        assertFalse(BENSON.isSameDoctor(editedBenson));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Doctor aliceCopy = new DoctorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BENSON));

        // different name -> returns false
        Doctor editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DoctorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different specialty -> returns false
        editedAlice = new DoctorBuilder(ALICE).withSpecialty(VALID_SPECIALTY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different yoe -> returns false
        editedAlice = new DoctorBuilder(ALICE).withYoe(VALID_YOE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DoctorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
