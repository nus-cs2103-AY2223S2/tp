package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null github username
        assertThrows(NullPointerException.class, () -> Github.isValidGithub(null));

        // invalid github usernames
        assertFalse(Github.isValidGithub("")); // empty string
        assertFalse(Github.isValidGithub(" ")); // spaces only
        assertFalse(Github.isValidGithub("-bob")); // start with hyphen
        assertFalse(Github.isValidGithub("bob-")); // end with hyphen
        assertFalse(Github.isValidGithub("hi--bob")); // consecutive hyphens
        assertFalse(Github.isValidGithub("bob is cool")); // spaces within characters
        assertFalse(Github.isValidGithub("mona-lisa-the-octocat-from-github-united")); // 40 characters

        // valid github usernames
        assertTrue(Github.isValidGithub("a")); // exactly 1 character
        assertTrue(Github.isValidGithub("bob-loves-cookies")); // hyphens in between characters
        assertTrue(Github.isValidGithub("b0bi5C00L")); // alphanumeric characters
    }
}
