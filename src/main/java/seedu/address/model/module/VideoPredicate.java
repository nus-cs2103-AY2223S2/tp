package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.video.Video;

/**
 * Tests that a {@code ReadOnlyLecture} is in user's input {@code ReadOnlyModule}.
 */
public class VideoPredicate implements Predicate<Video> {
    private final ReadOnlyLecture lecture;

    public VideoPredicate(ReadOnlyLecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public boolean test(Video video) {
        return lecture.hasVideo(video);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VideoPredicate // instanceof handles nulls
                && lecture.equals(((VideoPredicate) other).lecture)); // state check
    }
}
