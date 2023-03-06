package seedu.task.testutil;

import seedu.task.model.tracker.TagComparator;

/**
 * A utility class containing a list of {@code TagComparator} objects to be used in tests.
 */
public class TypicalTagComparator {

    public static final TagComparator CODE_3 = new TagComparatorBuilder().withOccurrence(3).build();
    public static final TagComparator EAT_1 = new TagComparatorBuilder()
            .withTag("EAT")
            .withOccurrence(1)
            .build();
    public static final TagComparator SLEEP_5 = new TagComparatorBuilder()
            .withTag("SLEEP")
            .withOccurrence(5)
            .build();

    private TypicalTagComparator() {} // prevents instantiation
}
