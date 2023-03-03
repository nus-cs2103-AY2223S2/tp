package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GitHubProfileTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitHubProfile(null));
    }

    @Test
    public void constructor_invalidProfile_throwsIllegalArgumentException() {
        String invalidProfile = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHubProfile(invalidProfile));
    }

    @Test
    public void isValidProfile() {
        // null profile
        assertThrows(NullPointerException.class, () -> GitHubProfile.isValidProfile(null));

        // invalid profile
        assertFalse(GitHubProfile.isValidProfile("")); // empty string
        assertFalse(GitHubProfile.isValidProfile(" ")); // spaces only
        assertFalse(GitHubProfile.isValidProfile("^")); // only non-alphanumeric characters
        assertFalse(GitHubProfile.isValidProfile("-chiayh")); // starts with hyphen
        assertFalse(GitHubProfile.isValidProfile("chiayh-")); // ends with hyphen
        assertFalse(GitHubProfile.isValidProfile("chiayh*")); // contains non-alphanumeric characters
        assertFalse(GitHubProfile.isValidProfile("chia yh")); // contains spaces
        assertFalse(GitHubProfile.isValidProfile("chia--yh")); // contains two consecutive hyphens
        assertFalse(GitHubProfile.isValidProfile("0123456789012345678901234567890123456789")); // exceeds 39 chars

        // valid profile
        assertTrue(GitHubProfile.isValidProfile("chiayh")); // alphabets only
        assertTrue(GitHubProfile.isValidProfile("123456")); // numbers only
        assertTrue(GitHubProfile.isValidProfile("chiayh2")); // alphanumeric characters
        assertTrue(GitHubProfile.isValidProfile("chia-yh")); // alphabets and hyphen
        assertTrue(GitHubProfile.isValidProfile("123-456")); // numbers and hyphen
        assertTrue(GitHubProfile.isValidProfile("chia-yh2")); // alphanumeric characters and hyphen
        assertTrue(GitHubProfile.isValidProfile("chia-yh-2")); // alphanumeric characters and hyphens
        assertTrue(GitHubProfile.isValidProfile("012345678901234567890123456789012345678")); // 39 chars
    }
}