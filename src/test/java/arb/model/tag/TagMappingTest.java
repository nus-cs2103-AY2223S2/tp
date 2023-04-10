package arb.model.tag;

import static arb.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.TagMappingBuilder;

public class TagMappingTest {

    @Test
    public void equals() {
        TagMapping friendTagMapping = new TagMappingBuilder().withTag(VALID_TAG_FRIEND).build();
        TagMapping husbandTagMapping = new TagMappingBuilder().withTag(VALID_TAG_HUSBAND).build();

        assertEquals(friendTagMapping, friendTagMapping); // same object
        assertNotEquals(friendTagMapping, 0); // different type
        assertNotEquals(friendTagMapping, husbandTagMapping); // different tag wrapping

        TagMapping editedFriendTagMapping = new TagMappingBuilder(friendTagMapping)
                .withNumberOfClientsTagged(1).build();
        assertNotEquals(friendTagMapping, editedFriendTagMapping); // different number of clients tagged
        editedFriendTagMapping = new TagMappingBuilder(editedFriendTagMapping)
                .withNumberOfClientsTagged(0).build();
        assertEquals(friendTagMapping, editedFriendTagMapping); // reverted client tagging
    }

    @Test
    public void isSameTagMapping() {
        TagMapping friendTagMapping = new TagMappingBuilder().withTag(VALID_TAG_FRIEND).build();
        TagMapping husbandTagMapping = new TagMappingBuilder().withTag(VALID_TAG_HUSBAND).build();
        Tag friendTag = new Tag(VALID_TAG_FRIEND);
        Tag husbandTag = new Tag(VALID_TAG_HUSBAND);
        assertTrue(friendTagMapping.isSameTagMapping(friendTagMapping)); // same object
        assertFalse(friendTagMapping.isSameTagMapping(husbandTagMapping)); // different tag wrapping
        assertTrue(friendTagMapping.isSameTagMapping(friendTag)); // same tag
        assertFalse(friendTagMapping.isSameTagMapping(husbandTag)); // different tag

        TagMapping editedFriendTagMapping = new TagMappingBuilder(friendTagMapping)
                .withNumberOfClientsTagged(1)
                .withNumberOfProjectsTagged(1).build();
        // different mapping numbers but same tag
        assertTrue(friendTagMapping.isSameTagMapping(editedFriendTagMapping));
    }
}
