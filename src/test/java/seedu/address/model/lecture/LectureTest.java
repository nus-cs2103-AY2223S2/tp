package seedu.address.model.lecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.exceptions.DuplicateVideoException;
import seedu.address.model.video.exceptions.VideoNotFoundException;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalVideos;

public class LectureTest {

    private final Lecture lecture = new LectureBuilder(TypicalLectures.CS2040S_WEEK_2).build();

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> lecture.getTags().remove(new Tag("Arrays")));
    }

    @Test
    public void getVideoList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> lecture.getVideoList().remove(0));
    }

    @Test
    public void isSameLecture_sameObject_returnsTrue() {
        assertTrue(lecture.isSameLecture(lecture));
    }

    @Test
    public void isSameLecture_nullVideo_returnsFalse() {
        assertFalse(lecture.isSameLecture(null));
    }

    @Test
    public void isSameLecture_sameName_returnsTrue() {
        Lecture editedLecture = new LectureBuilder(lecture).withVideos().withTags().build();

        assertTrue(lecture.isSameLecture(editedLecture));
    }

    @Test
    public void isSameLecture_differentName_returnsFalse() {
        Lecture editedLecture = new LectureBuilder(lecture).withName("Life is Bleak").build();

        assertFalse(lecture.isSameLecture(editedLecture));
    }

    public void hasVideo_nullVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> lecture.hasVideo((Video) null));
    }

    public void hasVideo_nullVideoName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> lecture.hasVideo((VideoName) null));
    }

    @Test
    public void hasVideo_videoNotInLecture_returnsFalse() {
        assertFalse(lecture.hasVideo(TypicalVideos.INTRO_VIDEO));
    }

    @Test
    public void removeVideo_nullVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> lecture.removeVideo(null));
    }

    @Test
    public void removeVideo_videoNotInLecture_throwsVideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> lecture.removeVideo(TypicalVideos.INTRO_VIDEO));
    }

    @Test
    public void removeVideo_videoInLecture_lectureDoesNotHaveVideo() {
        lecture.removeVideo(TypicalVideos.CONTENT_VIDEO);

        assertFalse(lecture.hasVideo(TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void addVideo_nullVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> lecture.addVideo(null));
    }

    @Test
    public void addVideo_videoNotInLecture_lectureHasVideo() {
        lecture.addVideo(TypicalVideos.INTRO_VIDEO);

        assertTrue(lecture.hasVideo(TypicalVideos.INTRO_VIDEO));
    }

    @Test
    public void addVideo_videoInLecture_throwsDuplicateVideoException() {
        assertThrows(DuplicateVideoException.class, () -> lecture.addVideo(TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void setVideo_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                lecture.setVideo(null, TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void setVideo_nullEditedVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                lecture.setVideo(TypicalVideos.CONTENT_VIDEO, null));
    }

    @Test
    public void setVideo_targetNotInLecture_throwsVideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () ->
                lecture.setVideo(TypicalVideos.INTRO_VIDEO, TypicalVideos.INTRO_VIDEO));
    }

    @Test
    public void setVideo_editedVideoIsDuplicate_throwsDuplicateVideoException() {
        assertThrows(DuplicateVideoException.class, () ->
                lecture.setVideo(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO));
    }

    @Test
    public void setVideo_editedVideoHasNoChange_videoNoChange() {
        lecture.setVideo(TypicalVideos.CONTENT_VIDEO, TypicalVideos.CONTENT_VIDEO);

        assertTrue(lecture.hasVideo(TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void setVideo_validTargetAndEditedVideo_videoReplaced() {
        lecture.setVideo(TypicalVideos.CONTENT_VIDEO, TypicalVideos.INTRO_VIDEO);

        assertFalse(lecture.hasVideo(TypicalVideos.CONTENT_VIDEO));
        assertTrue(lecture.hasVideo(TypicalVideos.INTRO_VIDEO));
    }

    @Test
    public void getVideo_nullVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> lecture.getVideo(null));
    }

    @Test
    public void getVideo_noVideoWithName_returnsNull() {
        assertEquals(lecture.getVideo(TypicalVideos.INTRO_VIDEO.getName()), null);
    }

    @Test
    public void getVideo_hasVideoWithName_returnsVideo() {
        assertEquals(lecture.getVideo(TypicalVideos.CONTENT_VIDEO.getName()), TypicalVideos.CONTENT_VIDEO);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Lecture lectureCopy = new LectureBuilder(lecture).build();

        assertTrue(lecture.equals(lectureCopy));

        // same object -> returns true
        assertTrue(lecture.equals(lecture));

        // null -> returns false
        assertFalse(lecture.equals(null));

        // different type -> returns false
        assertFalse(lecture.equals(42));

        // different name -> returns false
        Lecture editedLecture = new LectureBuilder(lecture).withName("Life is Bleak").build();
        assertFalse(lecture.equals(editedLecture));

        // diferent tags -> returns false
        editedLecture = new LectureBuilder(lecture).withTags("Lorem").build();
        assertFalse(lecture.equals(editedLecture));

        // different videos -> returns false
        editedLecture = new LectureBuilder(lecture).withVideos().build();
        assertFalse(lecture.equals(editedLecture));
    }

}
