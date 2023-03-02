package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.APPLE;
import static seedu.address.testutil.TypicalInternships.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship person = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(APPLE.isSameInternship(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameInternship(null));

        // same company name, all other attributes different -> returns true
        Internship editedApple = new InternshipBuilder(APPLE).withRole(VALID_ROLE_GOOGLE)
                .withStatus(VALID_STATUS_GOOGLE).withDate(VALID_DATE_GOOGLE).withTags(VALID_TAG_BACK).build();
        assertTrue(APPLE.isSameInternship(editedApple));

        // different company name, all other attributes same -> returns false
        editedApple = new InternshipBuilder(APPLE).withCompanyName(VALID_COMPANY_NAME_GOOGLE).build();
        assertFalse(APPLE.isSameInternship(editedApple));

        // company name differs in case, all other attributes same -> returns false
        Internship editedGoogle = new InternshipBuilder(GOOGLE)
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE.toLowerCase()).build();
        assertFalse(GOOGLE.isSameInternship(editedGoogle));

        // company name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_NAME_GOOGLE + " ";
        editedGoogle = new InternshipBuilder(GOOGLE).withCompanyName(nameWithTrailingSpaces).build();
        assertFalse(GOOGLE.isSameInternship(editedGoogle));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship aliceCopy = new InternshipBuilder(ALICE).build();
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
        Internship editedAlice = new InternshipBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternshipBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new InternshipBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
