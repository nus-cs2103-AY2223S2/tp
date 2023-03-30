package seedu.address.model.video;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalVideos;
import seedu.address.testutil.VideoBuilder;

public class VideoTest {

    private final Video video = TypicalVideos.ANALYSIS_VIDEO;

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> video.getTags().remove(new Tag("Analysis")));
    }

    @Test
    public void isSameVideo_sameObject_returnsTrue() {
        assertTrue(video.isSameVideo(video));
    }

    @Test
    public void isSameVideo_nullVideo_returnsFalse() {
        assertFalse(video.isSameVideo(null));
    }

    @Test
    public void isSameVideo_sameName_returnsTrue() {
        Video editedVideo = new VideoBuilder(video).withWatched(!video.hasWatched())
                .withTimestamp("02:42:42").withTags().build();
        assertTrue(video.isSameVideo(editedVideo));
    }

    @Test
    public void isSameVideo_differentName_returnsFalse() {
        Video editedVideo = new VideoBuilder(video).withName("Life is Bleak").build();
        assertFalse(video.isSameVideo(editedVideo));
    }

    @Test
    public void compareTo() {
        Video contentVideo = TypicalVideos.CONTENT_VIDEO;
        Video analysisVideo = TypicalVideos.ANALYSIS_VIDEO;

        assertTrue(contentVideo.compareTo(contentVideo) == 0);
        assertTrue(contentVideo.compareTo(analysisVideo) < 0);
        assertTrue(analysisVideo.compareTo(contentVideo) > 0);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Video videoCopy = new VideoBuilder(video).build();

        assertTrue(video.equals(videoCopy));

        // same object -> returns true
        assertTrue(video.equals(video));

        // null -> returns false
        assertFalse(video.equals(null));

        // different type -> returns false
        assertFalse(video.equals(42));

        // different name -> returns false
        Video editedVideo = new VideoBuilder(video).withName("Life is Bleak").build();
        assertFalse(video.equals(editedVideo));

        // different watched -> returns false
        editedVideo = new VideoBuilder(video).withWatched(!video.hasWatched()).build();
        assertFalse(video.equals(editedVideo));

        // different timestamp -> returns false
        editedVideo = new VideoBuilder(video).withTimestamp("02:42:42").build();
        assertFalse(video.equals(editedVideo));

        // diferent tags -> returns false
        editedVideo = new VideoBuilder(video).withTags("Lorem").build();
        assertFalse(video.equals(editedVideo));
    }

}
