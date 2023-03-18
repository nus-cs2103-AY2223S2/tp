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
        // null profile
        assertThrows(NullPointerException.class, () -> ProjectRepoName.isValidProjectRepoName(null));

        // invalid profile
        assertFalse(ProjectRepoName.isValidProjectRepoName(" ")); // spaces only
        assertFalse(ProjectRepoName.isValidProjectRepoName("^")); // only non-alphanumeric characters
        assertFalse(ProjectRepoName.isValidProjectRepoName("-chiayh")); // starts with hyphen
        assertFalse(ProjectRepoName.isValidProjectRepoName("chiayh-")); // ends with hyphen
        assertFalse(ProjectRepoName.isValidProjectRepoName("chiayh*")); // contains non-alphanumeric characters
        assertFalse(ProjectRepoName.isValidProjectRepoName("chia yh")); // contains spaces
        assertFalse(ProjectRepoName.isValidProjectRepoName("chia--yh")); // contains two consecutive hyphens
        assertFalse(ProjectRepoName.isValidProjectRepoName("0123456789012345678901234567890123456789")); // 40 chars

        // valid profile
        assertTrue(ProjectRepoName.isValidProjectRepoName("chiayh")); // alphabets only
        assertTrue(ProjectRepoName.isValidProjectRepoName("123456")); // numbers only
        assertTrue(ProjectRepoName.isValidProjectRepoName("chiayh2")); // alphanumeric characters
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh")); // alphabets and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("123-456")); // numbers and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh2")); // alphanumeric characters and hyphen
        assertTrue(ProjectRepoName.isValidProjectRepoName("chia-yh-2")); // alphanumeric characters and hyphens
        assertTrue(ProjectRepoName.isValidProjectRepoName("012345678901234567890123456789012345678")); // 39 chars
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
