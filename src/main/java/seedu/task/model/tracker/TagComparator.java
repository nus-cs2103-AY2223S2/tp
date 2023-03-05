package seedu.task.model.tracker;

public class TagComparator implements Comparable<TagComparator> {

    private String name;
    private int occurrences;

    public TagComparator(String name, int occurrences) {
        this.name = name;
        this.occurrences = occurrences;
    }

    public String getName() {
        return this.name;
    }

    public int getOccurrences() {
        return this.occurrences;
    }

    @Override
    public int compareTo(TagComparator entry) {
        return entry.occurrences - this.occurrences;
    }
}
