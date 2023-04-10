package seedu.patientist.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoleTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new RoleTag("invalid tag name"));
    }

    @Test
    public void isValidTagName() {
        assertTrue(RoleTag.isValidTagName(RoleTag.STAFF_TAG_NAME));
        assertTrue(RoleTag.isValidTagName(RoleTag.PATIENT_TAG_NAME));

        // RoleTag can only take values as determined in the class
        assertFalse(RoleTag.isValidTagName("invalid tag name"));
    }

    @Test
    public void equals() {
        RoleTag staffTag = new RoleTag(RoleTag.STAFF_TAG_NAME);
        RoleTag staffTagCopy = new RoleTag(RoleTag.STAFF_TAG_NAME);
        RoleTag patientTag = new RoleTag(RoleTag.PATIENT_TAG_NAME);

        // Same object -> equal
        assertEquals(staffTag, staffTag);

        // Diff object same role -> equal
        assertEquals(staffTag, staffTagCopy);

        // Diff role -> not equal
        assertNotEquals(staffTag, patientTag);
    }
}
