package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.GroupTag;

public class JsonAdaptedGroupTagTest {

    private static final String TAG_NAME = "Friend";
    private static final GroupTag GROUP_TAG = new GroupTag(TAG_NAME);

    @Test
    public void toModelType_validGroupTagDetails_returnsGroupTag() throws Exception {
        JsonAdaptedGroupTag jsonAdaptedGroupTag = new JsonAdaptedGroupTag(TAG_NAME);
        assertEquals(jsonAdaptedGroupTag.toModelType(), GROUP_TAG);

        jsonAdaptedGroupTag = new JsonAdaptedGroupTag(GROUP_TAG);
        assertEquals(jsonAdaptedGroupTag.toModelType(), GROUP_TAG);
    }

    @Test
    public void toModelType_nullNameAsString_throwsIllegalValueException() {
        JsonAdaptedGroupTag jsonAdaptedGroupTag = new JsonAdaptedGroupTag((String) null);
        assertThrows(IllegalValueException.class, jsonAdaptedGroupTag::toModelType);
    }

    @Test
    public void toModelType_nonEnglish_throwsIllegalValueException() {
        JsonAdaptedGroupTag jsonAdaptedGroupTag = new JsonAdaptedGroupTag("朋友");
        assertThrows(IllegalValueException.class, jsonAdaptedGroupTag::toModelType);
    }

    @Test
    public void toModelType_symbols_throwsIllegalValueException() {
        JsonAdaptedGroupTag jsonAdaptedGroupTag = new JsonAdaptedGroupTag("S/He");
        assertThrows(IllegalValueException.class, jsonAdaptedGroupTag::toModelType);
    }
}
