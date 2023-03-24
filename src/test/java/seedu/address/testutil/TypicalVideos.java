package seedu.address.testutil;

import seedu.address.model.video.Video;

/**
 * A utility class containing a list of {@code Video} objcts to be used in tests.
 */
public class TypicalVideos {

    public static final Video CONTENT_VIDEO = new VideoBuilder().withName("Vid 1")
            .withHasWatched(true).build();
    public static final Video ANALYSIS_VIDEO = new VideoBuilder().withName("Vid 2")
            .withHasWatched(true).withTags("Analysis", "MathHeavy").build();
    public static final Video INTRO_VIDEO = new VideoBuilder().withName("Vid 3")
            .withHasWatched(false).withTags("Intro").build();
    public static final Video REVISION_VIDEO = new VideoBuilder().withName("Vid 4")
            .withHasWatched(false).withTags("Revision").build();

}
