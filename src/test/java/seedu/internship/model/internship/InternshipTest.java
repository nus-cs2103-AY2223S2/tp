package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMMENT_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.APPLE;
import static seedu.internship.testutil.TypicalInternships.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.internship.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(APPLE.isSameInternship(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameInternship(null));

        // same company name, all other attributes different -> returns false
        Internship editedApple = new InternshipBuilder(APPLE).withRole(VALID_ROLE_GOOGLE)
                .withStatus(VALID_STATUS_GOOGLE).withDate(VALID_DATE_GOOGLE).withTags(VALID_TAG_BACK).build();
        assertFalse(APPLE.isSameInternship(editedApple));

        // different company name, all other attributes same -> returns false
        editedApple = new InternshipBuilder(APPLE).withCompanyName(VALID_COMPANY_NAME_GOOGLE).build();
        assertFalse(APPLE.isSameInternship(editedApple));

        // company name differs in case, all other attributes same -> returns true
        Internship editedGoogle = new InternshipBuilder(GOOGLE)
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE.toLowerCase()).build();
        assertTrue(GOOGLE.isSameInternship(editedGoogle));

        // role differs in case, all other attributes same -> returns true
        editedGoogle = new InternshipBuilder(GOOGLE)
                .withRole(VALID_ROLE_GOOGLE.toLowerCase()).build();
        assertTrue(GOOGLE.isSameInternship(editedGoogle));

        // company name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_NAME_GOOGLE + " ";
        editedGoogle = new InternshipBuilder(GOOGLE).withCompanyName(nameWithTrailingSpaces).build();
        assertFalse(GOOGLE.isSameInternship(editedGoogle));

        // different date -> returns false
        editedApple = new InternshipBuilder(APPLE).withDate(VALID_DATE_GOOGLE).build();
        assertFalse(APPLE.isSameInternship(editedApple));

        // different comment -> returns true
        editedApple = new InternshipBuilder(APPLE).withComment(VALID_COMMENT_GOOGLE).build();
        assertTrue(APPLE.isSameInternship(editedApple));

        // different tags -> returns true
        editedApple = new InternshipBuilder(APPLE).withTags(VALID_TAG_BACK).build();
        assertTrue(APPLE.isSameInternship(editedApple));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship appleCopy = new InternshipBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different internship -> returns false
        assertFalse(APPLE.equals(GOOGLE));

        // different name -> returns false
        Internship editedApple = new InternshipBuilder(APPLE).withCompanyName(VALID_COMPANY_NAME_GOOGLE).build();
        assertFalse(APPLE.equals(editedApple));

        // different role -> returns false
        editedApple = new InternshipBuilder(APPLE).withRole(VALID_ROLE_GOOGLE).build();
        assertFalse(APPLE.equals(editedApple));

        // different status -> returns false
        editedApple = new InternshipBuilder(APPLE).withStatus(VALID_STATUS_GOOGLE).build();
        assertFalse(APPLE.equals(editedApple));

        // different date -> returns false
        editedApple = new InternshipBuilder(APPLE).withDate(VALID_DATE_GOOGLE).build();
        assertFalse(APPLE.equals(editedApple));

        // different comment -> returns false
        editedApple = new InternshipBuilder(APPLE).withComment(VALID_COMMENT_GOOGLE).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new InternshipBuilder(APPLE).withTags(VALID_TAG_BACK).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
