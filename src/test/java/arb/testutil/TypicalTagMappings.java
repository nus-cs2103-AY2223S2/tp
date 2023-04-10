package arb.testutil;

import static arb.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_POTTERY;

import arb.model.tag.TagMapping;

/**
 * A utility class containing a list of {@code TagMapping} objects to be used in tests.
 */
public class TypicalTagMappings {

    public static final TagMapping HUSBAND_TAG = new TagMappingBuilder().withTag(VALID_TAG_HUSBAND).build();
    public static final TagMapping FRIEND_TAG = new TagMappingBuilder().withTag(VALID_TAG_FRIEND).build();

    public static final TagMapping PAINTING_TAG = new TagMappingBuilder().withTag(VALID_TAG_PAINTING).build();
    public static final TagMapping POTTERY_TAG = new TagMappingBuilder().withTag(VALID_TAG_POTTERY).build();

}
