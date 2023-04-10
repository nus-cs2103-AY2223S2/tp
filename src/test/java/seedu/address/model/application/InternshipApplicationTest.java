package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DEUTSCHE_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_NETWORK_ENGINEER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.LINKEDIN;
import static seedu.address.testutil.TypicalInternships.META;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipApplicationTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        InternshipApplication internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getQualifications().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(LINKEDIN.isSameApplication(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.isSameApplication(null));

        // same company name and job title, some other attributes different -> returns true
        InternshipApplication editedLinkedIn = new InternshipBuilder(LINKEDIN).withQualifications("Dancing").build();
        assertTrue(LINKEDIN.isSameApplication(editedLinkedIn));

        // different name, all other attributes same -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withCompanyName(VALID_COMPANY_NAME_DEUTSCHE_BANK).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));

        // name differs in case, all other attributes same -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN)
                            .withCompanyName(LINKEDIN.getCompanyName().toString().toLowerCase()).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = LINKEDIN.getCompanyName().toString() + " ";
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withCompanyName(nameWithTrailingSpaces).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternshipApplication linkedInCopy = new InternshipBuilder(LINKEDIN).build();
        assertTrue(LINKEDIN.equals(linkedInCopy));

        // same object -> returns true
        assertTrue(LINKEDIN.equals(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.equals(null));

        // different type -> returns false
        assertFalse(LINKEDIN.equals(5));

        // different person -> returns false
        assertFalse(LINKEDIN.equals(META));

        // different name -> returns false
        InternshipApplication editedLinkedIn = new InternshipBuilder(LINKEDIN)
            .withCompanyName(VALID_COMPANY_NAME_DEUTSCHE_BANK).build();
        assertFalse(LINKEDIN.equals(editedLinkedIn));

        // different job title -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withJobTitle(VALID_JOB_TITLE_NETWORK_ENGINEER).build();
        assertFalse(LINKEDIN.equals(editedLinkedIn));

    }
}
