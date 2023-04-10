package seedu.modtrek.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tag names
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("u l r"));

        // valid tag names
        assertTrue(Tag.isValidTagName("ULR"));
        assertTrue(Tag.isValidTagName("ulr"));
        assertTrue(Tag.isValidTagName("UNIVERSITY_LEVEL_REQUIREMENTS"));
        assertTrue(Tag.isValidTagName("University Level Requirements"));

        assertTrue(Tag.isValidTagName("CSF"));
        assertTrue(Tag.isValidTagName("Computer Science Foundation"));

        assertTrue(Tag.isValidTagName("ITP"));
        assertTrue(Tag.isValidTagName("IT Professionalism"));

        assertTrue(Tag.isValidTagName("CSBD"));
        assertTrue(Tag.isValidTagName("Computer Science Breadth and Depth"));

        assertTrue(Tag.isValidTagName("MS"));
        assertTrue(Tag.isValidTagName("Mathematics and Sciences"));

        assertTrue(Tag.isValidTagName("UE"));
        assertTrue(Tag.isValidTagName("Unrestricted Electives"));
    }

}
