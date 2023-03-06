package seedu.task.testutil;

import seedu.task.model.tag.Tag;
import seedu.task.model.tracker.TagComparator;

/**
 * A utility class to help with building TagComparator objects.
 */
public class TagComparatorBuilder {
    private static final String DEFAULT_TAG = "CODE";
    private static final int DEFAULT_OCCURRENCES = 1;
    private Tag tag;
    private int occurrences;

    /**
     * Creates a {@code TagComparatorBuilder} with the default details.
     */
    public TagComparatorBuilder() {
        this.tag = new Tag(DEFAULT_TAG);
        this.occurrences = DEFAULT_OCCURRENCES;
    }

    /**
     * Initializes the TagComparatorBuilder with the data of {@code tag}.
     */
    public TagComparatorBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Initializes the TagComparatorBuilder with the data of {@code num}.
     */
    public TagComparatorBuilder withOccurrence(int num) {
        this.occurrences = num;
        return this;
    }

    public TagComparator build() {
        return new TagComparator(tag, occurrences);
    }
}
