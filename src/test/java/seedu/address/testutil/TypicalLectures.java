package seedu.address.testutil;

import seedu.address.model.lecture.Lecture;

/**
 * A utility class containing a list of {@code Lecture} objcts to be used in tests.
 */
public class TypicalLectures {

    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek1()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_1 = new LectureBuilder().withName("Week 1")
            .withTags("Intro")
            .withVideos(TypicalVideos.INTRO_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek2()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_2 = new LectureBuilder().withName("Week 2")
            .withTags("Arrays", "LinkedList")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek3()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_3 = new LectureBuilder().withName("Week 3")
            .withTags("Sorting")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek4()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_4 = new LectureBuilder().withName("Week 4")
            .withTags("Arrays", "LinkedList")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek5()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_5 = new LectureBuilder().withName("Week 5")
            .withTags("Hashing")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek6()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_6 = new LectureBuilder().withName("Week 6")
            .withTags("BloomFilter")
            .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2040sWeek7()} instead. */
    @Deprecated
    public static final Lecture CS2040S_WEEK_7 = new LectureBuilder().withName("Week 7")
            .withTags("Revision")
            .withVideos(TypicalVideos.REVISION_VIDEO).build();

    /** @deprecated Use {@link TypicalLectures#getSt2334Topic1()} instead. */
    @Deprecated
    public static final Lecture ST2334_TOPIC_1 = new LectureBuilder().withName("Topic 1")
            .withTags("Intro")
            .withVideos(TypicalVideos.INTRO_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getSt2334Topic2()} instead. */
    @Deprecated
    public static final Lecture ST2334_TOPIC_2 = new LectureBuilder().withName("Topic 2")
            .withTags("Probability")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getSt2334Topic3()} instead. */
    @Deprecated
    public static final Lecture ST2334_TOPIC_3 = new LectureBuilder().withName("Topic 3")
            .withTags("RandomVariables")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getSt2334Topic4()} instead. */
    @Deprecated
    public static final Lecture ST2334_TOPIC_4 = new LectureBuilder().withName("Topic 4")
            .withTags("JointDistributions")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getSt2334Topic5()} instead. */
    @Deprecated
    public static final Lecture ST2334_TOPIC_5 = new LectureBuilder().withName("Topic 5")
            .withTags("Sampling")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();

    /** @deprecated Use {@link TypicalLectures#getCs2107Lecture1()} instead. */
    @Deprecated
    public static final Lecture CS2107_LECTURE_1 = new LectureBuilder().withName("Lecture 1")
            .withTags("Intro")
            .withVideos(TypicalVideos.INTRO_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2107Lecture2()} instead. */
    @Deprecated
    public static final Lecture CS2107_LECTURE_2 = new LectureBuilder().withName("Lecture 2")
            .withTags("Encryption")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2107Lecture3()} instead. */
    @Deprecated
    public static final Lecture CS2107_LECTURE_3 = new LectureBuilder().withName("Lecture 3")
            .withTags("EntityAuthentication")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @deprecated Use {@link TypicalLectures#getCs2107Lecture4()} instead. */
    @Deprecated
    public static final Lecture CS2107_LECTURE_4 = new LectureBuilder().withName("Lecture 4")
            .withTags("Authenticity")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    /** @Deprecated Use {@link TypicalLectures#getCs2107Lecture5()} instead. */
    @Deprecated
    public static final Lecture CS2107_LECTURE_5 = new LectureBuilder().withName("Lecture 5")
            .withTags("PublicKey")
            .withVideos(TypicalVideos.CONTENT_VIDEO).build();

    //=========================================== CS2040S Lectures ===========================================
    public static Lecture getCs2040sWeek1() {
        return new LectureBuilder().withName("Week 1")
                .withTags("Intro")
                .withVideos(TypicalVideos.INTRO_VIDEO).build();
    }

    public static Lecture getCs2040sWeek2() {
        return new LectureBuilder().withName("Week 2")
                .withTags("Arrays", "LinkedList")
                .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    }

    public static Lecture getCs2040sWeek3() {
        return new LectureBuilder().withName("Week 3")
                .withTags("Sorting")
                .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    }

    public static Lecture getCs2040sWeek4() {
        return new LectureBuilder().withName("Week 4")
                .withTags("Arrays", "LinkedList")
                .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    }

    public static Lecture getCs2040sWeek5() {
        return new LectureBuilder().withName("Week 5")
                .withTags("Hashing")
                .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    }

    public static Lecture getCs2040sWeek6() {
        return new LectureBuilder().withName("Week 6")
                .withTags("BloomFilter")
                .withVideos(TypicalVideos.CONTENT_VIDEO, TypicalVideos.ANALYSIS_VIDEO).build();
    }

    public static Lecture getCs2040sWeek7() {
        return new LectureBuilder().withName("Week 7")
                .withTags("Revision")
                .withVideos(TypicalVideos.REVISION_VIDEO).build();
    }

    //=========================================== ST2334 Lectures ============================================
    public static Lecture getSt2334Topic1() {
        return new LectureBuilder().withName("Topic 1")
                .withTags("Intro")
                .withVideos(TypicalVideos.INTRO_VIDEO).build();
    }

    public static Lecture getSt2334Topic2() {
        return new LectureBuilder().withName("Topic 2")
                .withTags("Probability")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }
    public static Lecture getSt2334Topic3() {
        return new LectureBuilder().withName("Topic 3")
                .withTags("RandomVariables")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    public static Lecture getSt2334Topic4() {
        return new LectureBuilder().withName("Topic 4")
                .withTags("JointDistributions")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    public static Lecture getSt2334Topic5() {
        return new LectureBuilder().withName("Topic 5")
                .withTags("Sampling")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    //=========================================== CS2107 Lectures ============================================
    public static Lecture getCs2107Lecture1() {
        return new LectureBuilder().withName("Lecture 1")
                .withTags("Intro")
                .withVideos(TypicalVideos.INTRO_VIDEO).build();
    }

    public static Lecture getCs2107Lecture2() {
        return new LectureBuilder().withName("Lecture 2")
                .withTags("Encryption")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    public static Lecture getCs2107Lecture3() {
        return new LectureBuilder().withName("Lecture 3")
                .withTags("EntityAuthentication")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    public static Lecture getCs2107Lecture4() {
        return new LectureBuilder().withName("Lecture 4")
                .withTags("Authenticity")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

    public static Lecture getCs2107Lecture5() {
        return new LectureBuilder().withName("Lecture 5")
                .withTags("PublicKey")
                .withVideos(TypicalVideos.CONTENT_VIDEO).build();
    }

}
