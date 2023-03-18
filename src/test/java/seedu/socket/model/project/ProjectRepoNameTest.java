package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;
import static seedu.socket.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectRepoNameTest {
    private static final ProjectRepoName ALPHA_REPO_NAME = new ProjectRepoName(VALID_PROJECT_REPO_NAME_ALPHA);
    private static final ProjectRepoName BRAVO_REPO_NAME = new ProjectRepoName(VALID_PROJECT_REPO_NAME_BRAVO);
    private static final ProjectRepoName EMPTY_REPO_NAME = new ProjectRepoName("");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectRepoName(null));
    }

    @Test
    public void constructor_invalidRepoName_throwsIllegalArgumentException() {
        String invalidRepoName = " ";
        assertThrows(IllegalArgumentException.class, () -> new ProjectRepoName(invalidRepoName));
    }

    @Test
    public void testIsValidRepoName() {
        // null repo
        assertThrows(NullPointerException.class, () -> ProjectRepoName.isValidProjectRepoName(null));

        // reserved repo names
        assertFalse(ProjectRepoName.isValidProjectRepoName(".")); // .
        assertFalse(ProjectRepoName.isValidProjectRepoName("..")); // ..

        // invalid repo
        assertFalse(ProjectRepoName.isValidProjectRepoName(" ")); // spaces only
        assertFalse(ProjectRepoName.isValidProjectRepoName("^")); // only invalid non-alphanumeric characters
        assertFalse(ProjectRepoName.isValidProjectRepoName("chiayh*")); // contains invalid non-alphanumeric characters
        assertFalse(ProjectRepoName.isValidProjectRepoName("chia yh")); // contains spaces
        assertFalse(ProjectRepoName.isValidProjectRepoName("01234567890123456789012345678901234567890123456789"
                + "012345678901234567890123456789012345678901234567890")); // 101 chars

        // valid repo
        assertTrue(ProjectRepoName.isValidProjectRepoName("-")); // hyphen only
        assertTrue(ProjectRepoName.isValidProjectRepoName("_")); // underscore only
        assertTrue(ProjectRepoName.isValidProjectRepoName("chiayh")); // alphabets only
        assertTrue(ProjectRepoName.isValidProjectRepoName("123456")); // numbers only
        assertTrue(ProjectRepoName.isValidProjectRepoName("...")); // periods only
        assertTrue(ProjectRepoName.isValidProjectRepoName("---")); // hyphens only
        assertTrue(ProjectRepoName.isValidProjectRepoName("___")); // underscores only
        assertTrue(ProjectRepoName.isValidProjectRepoName("chiayh2")); // alphanumeric characters
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh")); // alphabets and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia_yh")); // alphabets and underscore
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia.yh")); // alphabets and period
        assertTrue(ProjectRepoName.isValidProjectRepoName("123-456")); // numbers and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("123_456")); // numbers and underscore
        assertTrue(ProjectRepoName.isValidProjectRepoName("123.456")); // numbers and period
        assertTrue(ProjectRepoName.isValidProjectRepoName("..--")); // periods and hyphens
        assertTrue(ProjectRepoName.isValidProjectRepoName("..__")); // periods and underscores
        assertTrue(ProjectRepoName.isValidProjectRepoName("__--")); // underscores and hyphens
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh2")); // alphanumeric characters and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh-2")); // alphanumeric characters and hyphens
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia.yh2")); // alphanumeric characters and period
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia.yh.2")); // alphanumeric characters and periods
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia_yh2")); // alphanumeric characters and underscore
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia_yh_2")); // alphanumeric characters and underscores
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia.yh_2-4")); // every valid character
        assertTrue(ProjectRepoName.isValidProjectRepoName("01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789")); // 100 chars
    }

    @Test
    public void testIsEmptyRepoName() {
        assertTrue(EMPTY_REPO_NAME.isEmptyRepoName());
    }
    @Test
    public void testToString() {
        assertTrue(VALID_PROJECT_REPO_NAME_ALPHA.equals(ALPHA_REPO_NAME.toString()));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        ProjectRepoName alphaRepoCopy = new ProjectRepoName(VALID_PROJECT_REPO_NAME_ALPHA);
        assertTrue(ALPHA_REPO_NAME.equals(alphaRepoCopy));

        // same object -> returns true
        assertTrue(ALPHA_REPO_NAME.equals(ALPHA_REPO_NAME));

        // null -> returns false
        assertFalse(ALPHA_REPO_NAME.equals(null));

        // different type -> returns false
        assertFalse(ALPHA_REPO_NAME.equals(5));

        // different profile -> returns false
        assertFalse(ALPHA_REPO_NAME.equals(BRAVO_REPO_NAME));
    }
}
