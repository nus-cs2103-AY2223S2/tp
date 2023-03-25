package seedu.dengue.model.range;

public class Range<T> {

    public final Start<T> start;
    public final End<T> end;

    /**
     * Constructs a {@code Range}.
     * Start and End should form a valid range, i.e. Start < End.
     * @param start A Start.
     * @param end An End.
     */
    public Range(Start<T> start, End<T> end) {
        this.start = start;
        this.end = end;
    }

    public Start<T> getStart() {
        return start;
    }

    public End<T> getEnd() {
        return end;
    }

}
