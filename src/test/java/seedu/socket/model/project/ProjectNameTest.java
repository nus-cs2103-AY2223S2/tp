package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectName(null));
    }

    @Test
    public void constructor_invalidProjectName_throwsIllegalArgumentException() {
        String invalidProjectName = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectName(invalidProjectName));
    }

    @Test
    public void isValidProjectName() {
        // null name
        assertThrows(NullPointerException.class, () -> ProjectName.isValidProjectName(null));

        // invalid name
        assertFalse(ProjectName.isValidProjectName("")); // empty string
        assertFalse(ProjectName.isValidProjectName(" ")); // spaces only
        assertFalse(ProjectName.isValidProjectName("^")); // only non-alphanumeric characters
        assertFalse(ProjectName.isValidProjectName("peter  parker")); // consecutive whitespaces
        assertFalse(ProjectName.isValidProjectName("peter*")); // contains non-alphanumeric characters
        assertFalse(ProjectName.isValidProjectName(" peter")); // leading space
        assertFalse(ProjectName.isValidProjectName("peter ")); // trailing space

        // valid name
        assertTrue(ProjectName.isValidProjectName("peter jack")); // alphabets only
        assertTrue(ProjectName.isValidProjectName("12345")); // numbers only
        assertTrue(ProjectName.isValidProjectName("peter the 2nd")); // alphanumeric characters
        assertTrue(ProjectName.isValidProjectName("Capital Tan")); // with capital letters
        assertTrue(ProjectName.isValidProjectName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void testEquals() {
        ProjectName capitalisedName = new ProjectName("Alice");
        ProjectName lowercaseName = new ProjectName("alice");
        ProjectName differentName = new ProjectName("Bob");

        assertTrue(capitalisedName.equals(lowercaseName));
        assertFalse(capitalisedName.equals(differentName));
    }
}
