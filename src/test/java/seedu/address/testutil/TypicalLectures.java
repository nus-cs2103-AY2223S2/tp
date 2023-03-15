package seedu.address.testutil;

import seedu.address.model.lecture.Lecture;

/**
 * A utility class containing a list of {@code Lecture} objcts to be used in tests.
 */
public class TypicalLectures {

    public static final Lecture CS2040S_WEEK_1 = new LectureBuilder().withName("Week 1")
            .withTags("Intro")
            .withVideos(TypicalVideos.INTRO_VIDEO).build();
    public static final Lecture CS2040S_WEEK_2 = new LectureBuilder().withName("Week 2")
            .withTags("Arrays", "LinkedList")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    public static final Lecture CS2040S_WEEK_3 = new LectureBuilder().withName("Week 3")
            .withTags("Sorting")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    public static final Lecture CS2040S_WEEK_4 = new LectureBuilder().withName("Week 4")
            .withTags("Arrays", "LinkedList")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    public static final Lecture CS2040S_WEEK_5 = new LectureBuilder().withName("Week 5")
            .withTags("Hashing")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    public static final Lecture CS2040S_WEEK_6 = new LectureBuilder().withName("Week 6")
            .withTags("BloomFilter")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    public static final Lecture CS2040S_WEEK_7 = new LectureBuilder().withName("Week 7")
            .withTags("Revision")
            .withVideos(TypicalVideos.REVISION_VIDEO).build();

    public static final Lecture ST2334_TOPIC_1 = new LectureBuilder().withName("Topic 1")
            .withTags("Intro")
            .withVideos(TypicalVideos.INTRO_VIDEO).build();
    public static final Lecture ST2334_TOPIC_2 = new LectureBuilder().withName("Topic 2")
            .withTags("Probability")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    public static final Lecture ST2334_TOPIC_3 = new LectureBuilder().withName("Topic 3")
            .withTags("RandomVariables")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    public static final Lecture ST2334_TOPIC_4 = new LectureBuilder().withName("Topic 4")
            .withTags("JointDistributions")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    public static final Lecture ST2334_TOPIC_5 = new LectureBuilder().withName("Topic 5")
            .withTags("Sampling")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();

}
