package seedu.address.model.platform;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_GLINTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_LINKEDIN;
import static seedu.address.testutil.TypicalPlatforms.GLINTS;
import static seedu.address.testutil.TypicalPlatforms.LINKEDIN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PlatformBuilder;

public class PlatformTest {

    @Test
    public void isSamePlatform() {
        // same object -> returns true
        assertTrue(LINKEDIN.isSamePlatform(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.isSamePlatform(null));

        // name differs in case -> false
        Platform editedGlints = new PlatformBuilder(GLINTS).withName(VALID_PLATFORM_NAME_GLINTS.toLowerCase()).build();
        assertFalse(GLINTS.isSamePlatform(editedGlints));

        // name has trailing spaces -> true
        String nameWithTrailingSpaces = VALID_PLATFORM_NAME_GLINTS + " ";
        editedGlints = new PlatformBuilder(GLINTS).withName(nameWithTrailingSpaces).build();
        assertTrue(GLINTS.isSamePlatform(editedGlints));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Platform linkedinCopy = new PlatformBuilder(LINKEDIN).build();
        assertTrue(LINKEDIN.equals(linkedinCopy));

        // same object -> returns true
        assertTrue(LINKEDIN.equals(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.equals(null));

        //different type -> return false
        assertFalse(LINKEDIN.equals(5));

        //different platform -> return false
        assertFalse(LINKEDIN.equals(GLINTS));

        //different name -> return false
        Platform editedGlints = new PlatformBuilder(GLINTS).withName(VALID_PLATFORM_NAME_LINKEDIN).build();
        assertFalse(GLINTS.equals(editedGlints));
    }

    @Test
    public void stringTest() {
        String expected = "Linkedin";
        assertTrue(LINKEDIN.toString().equals(expected));
    }
}
