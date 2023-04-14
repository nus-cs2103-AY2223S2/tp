package seedu.powercards.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.model.tag.Tag.TagName.EASY;
import static seedu.powercards.model.tag.Tag.TagName.HARD;
import static seedu.powercards.model.tag.Tag.TagName.MEDIUM;
import static seedu.powercards.model.tag.Tag.TagName.UNTAGGED;
import static seedu.powercards.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // valid tag name
        List<String> validTagNameTestList = List.of("easy", "medium", "hard", "easY", "MediUm", "HARD");
        for (String name : validTagNameTestList) {
            assertTrue(Tag.isValidTagName(name));
        }

        // invalid tag name
        assertFalse(Tag.isValidTagName("random"));
        assertFalse(Tag.isValidTagName("easyyy"));
        assertFalse(Tag.isValidTagName("easy medium"));
    }

    @Test
    public void hashCode_validTag_returnsHashCode() {
        Tag easyTag = new Tag(EASY);
        Tag mediumTag = new Tag(MEDIUM);
        Tag hardTag = new Tag(HARD);

        assertEquals(easyTag.hashCode(), easyTag.hashCode());
        assertEquals(mediumTag.hashCode(), mediumTag.hashCode());
        assertEquals(hardTag.hashCode(), hardTag.hashCode());
    }

    @Test
    public void getTagName_validTagName_returnsCorrectTagName() {
        Tag easyTag = new Tag(EASY);
        Tag mediumTag = new Tag(MEDIUM);
        Tag hardTag = new Tag(HARD);
        Tag untaggedTag = new Tag(UNTAGGED);

        assertEquals(EASY, easyTag.getTagName());
        assertEquals(MEDIUM, mediumTag.getTagName());
        assertEquals(HARD, hardTag.getTagName());
        assertEquals(UNTAGGED, untaggedTag.getTagName());
    }

}
