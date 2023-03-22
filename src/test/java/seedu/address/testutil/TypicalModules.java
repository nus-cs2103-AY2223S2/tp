package seedu.address.testutil;

import seedu.address.model.Tracker;
import seedu.address.model.module.Module;

/**
 * A utility class containing list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    /** @deprecated Use {@link TypicalModules#getCs2040s()} instead. */
    @Deprecated
    public static final Module CS2040S = new ModuleBuilder()
            .withCode("CS2040S").withName("Data Structures and Algorithms")
            .withTags("Heavy", "Math", "Analysis")
            .withLectures(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.CS2040S_WEEK_2,
                    TypicalLectures.CS2040S_WEEK_3, TypicalLectures.CS2040S_WEEK_4,
                    TypicalLectures.CS2040S_WEEK_5, TypicalLectures.CS2040S_WEEK_6,
                    TypicalLectures.CS2040S_WEEK_7)
            .build();
    /** @deprecated Use {@link TypicalModules#getSt2334()} instead. */
    @Deprecated
    public static final Module ST2334 = new ModuleBuilder()
            .withCode("ST2334").withName("Probability and Statistics")
            .withTags("Math", "Probability")
            .withLectures(TypicalLectures.ST2334_TOPIC_1, TypicalLectures.ST2334_TOPIC_2,
                    TypicalLectures.ST2334_TOPIC_3, TypicalLectures.ST2334_TOPIC_4,
                    TypicalLectures.ST2334_TOPIC_5)
            .build();

    // Not included in typical tracker
    /** @deprecated Use {@link TypicalModules#getCs2107()} instead. */
    @Deprecated
    public static final Module CS2107 = new ModuleBuilder()
            .withCode("CS2107").withName("Introduction to Information Security")
            .withTags("Security", "ContentHeavy")
            .withLectures(TypicalLectures.CS2107_LECTURE_1, TypicalLectures.CS2107_LECTURE_2,
                    TypicalLectures.CS2107_LECTURE_3, TypicalLectures.CS2107_LECTURE_4,
                    TypicalLectures.CS2107_LECTURE_5)
            .build();

    public static Module getCs2040s() {
        return new ModuleBuilder()
                .withCode("CS2040S").withName("Data Structures and Algorithms")
                .withTags("Heavy", "Math", "Analysis")
                .withLectures(TypicalLectures.getCs2040sWeek1(), TypicalLectures.getCs2040sWeek2(),
                        TypicalLectures.getCs2040sWeek3(), TypicalLectures.getCs2040sWeek4(),
                        TypicalLectures.getCs2040sWeek5(), TypicalLectures.getCs2040sWeek6(),
                        TypicalLectures.getCs2040sWeek7())
                .build();
    }

    public static Module getSt2334() {
        return new ModuleBuilder()
                .withCode("ST2334").withName("Probability and Statistics")
                .withTags("Math", "Probability")
                .withLectures(TypicalLectures.getSt2334Topic1(), TypicalLectures.getSt2334Topic2(),
                        TypicalLectures.getSt2334Topic3(), TypicalLectures.getSt2334Topic4(),
                        TypicalLectures.getSt2334Topic5())
                .build();
    }

    // Not included in typical tracker
    public static Module getCs2107() {
        return new ModuleBuilder()
                .withCode("CS2107").withName("Introduction to Information Security")
                .withTags("Security", "ContentHeavy")
                .withLectures(TypicalLectures.getCs2107Lecture1(), TypicalLectures.getCs2107Lecture2(),
                        TypicalLectures.getCs2107Lecture3(), TypicalLectures.getCs2107Lecture4(),
                        TypicalLectures.getCs2107Lecture5())
                .build();
    }

    /**
     * Returns a {@code Tracker} with all typical modules.
     *
     * @return A {@code Tracker} with all typical modules.
     */
    public static Tracker getTypicalTracker() {
        Tracker tracker = new Tracker();
        tracker.addModule(getCs2040s());
        tracker.addModule(getSt2334());

        return tracker;
    }
}
