package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.testutil.TypicalLectures;

public class JsonAdaptedLectureTest {

    private static final String INVALID_NAME = "Lecture_01**";
    private static final String INVALID_TAG = "H@rd";

    private static final ReadOnlyLecture ORIGINAL_LECTURE = TypicalLectures.getCs2040sWeek1();
    private static final String VALID_NAME = ORIGINAL_LECTURE.getName().name;
    private static final List<JsonAdaptedVideo> VALID_VIDEOS = ORIGINAL_LECTURE.getVideoList().stream()
            .map(JsonAdaptedVideo::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = ORIGINAL_LECTURE.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validLectureDetails_returnsLecture() throws Exception {
        JsonAdaptedLecture adaptedLecture = new JsonAdaptedLecture(ORIGINAL_LECTURE);
        assertEquals(ORIGINAL_LECTURE, adaptedLecture.toModelType());
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

    @Test
    public void toModelType_duplicateVideos_throwsIllegalValueException() {
        List<JsonAdaptedVideo> dupicatedVideos = new ArrayList<>(VALID_VIDEOS);
        dupicatedVideos.addAll(VALID_VIDEOS);

        JsonAdaptedLecture lecture = new JsonAdaptedLecture(VALID_NAME, dupicatedVideos, VALID_TAGS);
        String expectedMessage = JsonAdaptedLecture.MESSAGE_DUPLICATE_VIDEO;
        assertThrows(IllegalValueException.class, expectedMessage, lecture::toModelType);
    }

    @Test
    public void toModelType_nullInVideos_nullValueIgnored() throws IllegalValueException {
        List<JsonAdaptedVideo> videosContainingNull = new ArrayList<>(VALID_VIDEOS);
        videosContainingNull.add(null);

        JsonAdaptedLecture lecture = new JsonAdaptedLecture(VALID_NAME, videosContainingNull, VALID_TAGS);
        assertEquals(ORIGINAL_LECTURE, lecture.toModelType());
    }

    @Test
    public void toModelType_duplicateTags_duplicatesIgnored() throws Exception {
        List<JsonAdaptedTag> duplicatedTags = new ArrayList<>(VALID_TAGS);
        duplicatedTags.addAll(VALID_TAGS);

        JsonAdaptedLecture lecture = new JsonAdaptedLecture(VALID_NAME, VALID_VIDEOS, duplicatedTags);
        assertEquals(ORIGINAL_LECTURE, lecture.toModelType());
    }

    @Test
    public void toModelType_duplicateTags_nullValueIgnored() throws Exception {
        List<JsonAdaptedTag> tagsContainingNull = new ArrayList<>(VALID_TAGS);
        tagsContainingNull.add(null);

        JsonAdaptedLecture lecture = new JsonAdaptedLecture(VALID_NAME, VALID_VIDEOS, tagsContainingNull);
        assertEquals(ORIGINAL_LECTURE, lecture.toModelType());
    }

}
