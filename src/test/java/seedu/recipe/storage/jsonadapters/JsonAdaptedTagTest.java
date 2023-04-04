package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.tag.Tag;

//@@author alson001
public class JsonAdaptedTagTest {
    private static final String VALID_TAG_NAME = "healthy";
    private static final String INVALID_TAG_NAME = "#fatty";

    @Test
    public void constructor_validTagName_returnsJsonAdaptedTag() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(VALID_TAG_NAME);
        assertEquals(VALID_TAG_NAME, adaptedTag.getTagName());
    }

    @Test
    public void constructor_validTag_returnsJsonAdaptedTag() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(new Tag(VALID_TAG_NAME));
        assertEquals(VALID_TAG_NAME, adaptedTag.getTagName());
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(INVALID_TAG_NAME);
        assertThrows(IllegalValueException.class, adaptedTag::toModelType);
    }

    @Test
    public void toModelType_validTagName_returnsTag() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(VALID_TAG_NAME);
        assertEquals(new Tag(VALID_TAG_NAME), adaptedTag.toModelType());
    }

    @Test
    public void toModelType_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(INVALID_TAG_NAME);
        assertThrows(IllegalValueException.class, adaptedTag::toModelType);
    }
}
