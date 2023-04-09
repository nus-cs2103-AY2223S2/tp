package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_PLANT;
import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.tag.Tag;


public class JsonAdaptedTagTest {
    @Test
    public void toModelType_validTags_returnTags() throws Exception {
        Tag validTag = new Tag(VALID_TAG_PLANT);
        JsonAdaptedTag tag = new JsonAdaptedTag(validTag);
        assertEquals(validTag, tag.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(INVALID_TAG_DESC);
        assertThrows(IllegalValueException.class, tag::toModelType);
    }
}
