package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ProjectRepoHostTest {
    private static final ProjectRepoHost ALPHA_REPO_HOST = new ProjectRepoHost(VALID_PROJECT_REPO_HOST_ALPHA);
    private static final ProjectRepoHost BRAVO_REPO_HOST = new ProjectRepoHost(VALID_PROJECT_REPO_HOST_BRAVO);
    private static final ProjectRepoHost EMPTY_REPO_HOST = new ProjectRepoHost("");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectRepoHost(null));
    }

    @Test
    public void constructor_invalidHost_throwsIllegalArgumentException() {
        String invalidProfile = " ";
        assertThrows(IllegalArgumentException.class, () -> new ProjectRepoHost(invalidProfile));
    }

    @Test
    public void testIsValidHost() {
        // null host
        assertThrows(NullPointerException.class, () -> ProjectRepoHost.isValidProjectRepoHost(null));

        // invalid host
        assertFalse(ProjectRepoHost.isValidProjectRepoHost(" ")); // spaces only
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("^")); // only non-alphanumeric characters
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("-chiayh")); // starts with hyphen
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("chiayh-")); // ends with hyphen
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("chiayh*")); // contains non-alphanumeric characters
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("chia yh")); // contains spaces
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("chia--yh")); // contains two consecutive hyphens
        assertFalse(ProjectRepoHost.isValidProjectRepoHost("0123456789012345678901234567890123456789")); // 40 chars

        // valid host
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("chiayh")); // alphabets only
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("123456")); // numbers only
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("chiayh2")); // alphanumeric characters
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("chia-yh")); // alphabets and hyphen
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("123-456")); // numbers and hyphen
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("chia-yh2")); // alphanumeric characters and hyphen
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("chia-yh-2")); // alphanumeric characters and hyphens
        assertTrue(ProjectRepoHost.isValidProjectRepoHost("012345678901234567890123456789012345678")); // 39 chars
    }
    @Test
    public void testIsEmptyRepoHost() {
        assertTrue(EMPTY_REPO_HOST.isEmptyRepoHost());
    }
    @Test
    public void testToString() {
        assertTrue(VALID_PROJECT_REPO_HOST_ALPHA.equals(ALPHA_REPO_HOST.toString()));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        ProjectRepoHost amyCopy = new ProjectRepoHost(VALID_PROJECT_REPO_HOST_ALPHA);
        assertTrue(ALPHA_REPO_HOST.equals(amyCopy));

        // same object -> returns true
        assertTrue(ALPHA_REPO_HOST.equals(ALPHA_REPO_HOST));

        // null -> returns false
        assertFalse(ALPHA_REPO_HOST.equals(null));

        // different type -> returns false
        assertFalse(ALPHA_REPO_HOST.equals(5));

        // different host -> returns false
        assertFalse(ALPHA_REPO_HOST.equals(BRAVO_REPO_HOST));
    }
}
