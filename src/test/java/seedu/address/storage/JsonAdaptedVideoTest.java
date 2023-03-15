package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.TypicalVideos;

public class JsonAdaptedVideoTest {

    private static final String INVALID_NAME = "Video_01**";
    private static final String INVALID_TAG = "H@rd";

    private static final String VALID_NAME = TypicalVideos.CONTENT_VIDEO.getName().name;
    private static final boolean VALID_HAS_WATCHED = false;
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalVideos.CONTENT_VIDEO.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validVideoDetails_returnsVideo() throws Exception {
        JsonAdaptedVideo video = new JsonAdaptedVideo(TypicalVideos.CONTENT_VIDEO);
        assertEquals(TypicalVideos.CONTENT_VIDEO, video.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedVideo video = new JsonAdaptedVideo(INVALID_NAME, VALID_HAS_WATCHED, VALID_TAGS);
        String expectedMessage = VideoName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, video::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedVideo video = new JsonAdaptedVideo(null, VALID_HAS_WATCHED, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedVideo.MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, video::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedVideo video =
                new JsonAdaptedVideo(VALID_NAME, VALID_HAS_WATCHED, invalidTags);
        assertThrows(IllegalValueException.class, video::toModelType);
    }

}
