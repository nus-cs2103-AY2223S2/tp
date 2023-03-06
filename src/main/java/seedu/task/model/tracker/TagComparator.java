package seedu.task.model.tracker;

import seedu.task.model.tag.Tag;

/**
 * Encapsulate a tag-occurrence unit to facilitate sorting
 */
public class TagComparator implements Comparable<TagComparator> {

    private Tag name;
    private int occurrences;

    /**
     * Creates an instance of tag-pair entity
     * @param name
     * @param occurrences
     */
    public TagComparator(Tag name, int occurrences) {
        this.name = name;
        this.occurrences = occurrences;
    }

    public String getName() {
        return this.name.toString();
    }

    public int getOccurrences() {
        return this.occurrences;
    }

    @Override
    public int compareTo(TagComparator o) {
        return o.occurrences - this.occurrences;
    }
}
