package seedu.wife.testutil;

import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_NEW;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_USED;

import seedu.wife.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTag {
    public static final Tag DAIRY_TAG = new TagBuilder()
            .withTagName(VALID_TAG_DAIRY)
            .build();

    public static final Tag CHOCOLATE_TAG = new TagBuilder()
            .withTagName(VALID_TAG_CHOCOLATE)
            .build();

    public static final Tag NEW_TAG = new TagBuilder()
            .withTagName(VALID_TAG_NEW)
            .build();

    public static final Tag USED_TAG = new TagBuilder()
            .withTagName(VALID_TAG_USED)
            .build();
}
