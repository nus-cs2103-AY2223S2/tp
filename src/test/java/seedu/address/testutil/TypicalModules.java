package seedu.address.testutil;

import seedu.address.model.Tracker;
import seedu.address.model.module.Module;

/**
 * A utility class containing list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2040S = new ModuleBuilder()
            .withCode("CS2040S").withName("Data Structures and Algorithms")
            .withTags("Heavy", "Math", "Analysis")
            .withLectures(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.CS2040S_WEEK_2,
                    TypicalLectures.CS2040S_WEEK_3, TypicalLectures.CS2040S_WEEK_4,
                    TypicalLectures.CS2040S_WEEK_5, TypicalLectures.CS2040S_WEEK_6,
                    TypicalLectures.CS2040S_WEEK_7)
            .build();
    public static final Module ST2334 = new ModuleBuilder()
            .withCode("ST2334").withName("Probability and Statistics")
            .withTags("Math", "Probability")
            .withLectures(TypicalLectures.ST2334_TOPIC_1, TypicalLectures.ST2334_TOPIC_2,
                    TypicalLectures.ST2334_TOPIC_3, TypicalLectures.ST2334_TOPIC_4,
                    TypicalLectures.ST2334_TOPIC_5)
            .build();

    /**
     * Returns a {@code Tracker} with all typical modules.
     *
     * @return A {@code Tracker} with all typical modules.
     */
    public static Tracker getTypicalTracker() {
        Tracker tracker = new Tracker();
        tracker.addModule(CS2040S);
        tracker.addModule(ST2334);

        return tracker;
    }
}
