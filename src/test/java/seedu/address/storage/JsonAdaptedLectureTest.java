package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lecture.LectureName;
import seedu.address.testutil.TypicalLectures;

public class JsonAdaptedLectureTest {

    private static final String INVALID_NAME = "Lecture_01**";
    private static final String INVALID_TAG = "H@rd";

    private static final String VALID_NAME = TypicalLectures.CS2040S_WEEK_1.getName().name;
    private static final List<JsonAdaptedVideo> VALID_VIDEOS = TypicalLectures.CS2040S_WEEK_1.getVideoList().stream()
            .map(JsonAdaptedVideo::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalLectures.CS2040S_WEEK_1.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validLectureDetails_returnsLecture() throws Exception {
        JsonAdaptedLecture lecture = new JsonAdaptedLecture(TypicalLectures.CS2040S_WEEK_1);
        assertEquals(TypicalLectures.CS2040S_WEEK_1, lecture.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedLecture lecture = new JsonAdaptedLecture(INVALID_NAME, VALID_VIDEOS, VALID_TAGS);
        String expectedMessage = LectureName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lecture::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedLecture lecture = new JsonAdaptedLecture(null, VALID_VIDEOS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedLecture.MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, lecture::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedLecture lecture =
                new JsonAdaptedLecture(VALID_NAME, VALID_VIDEOS, invalidTags);
        assertThrows(IllegalValueException.class, lecture::toModelType);
    }

}
