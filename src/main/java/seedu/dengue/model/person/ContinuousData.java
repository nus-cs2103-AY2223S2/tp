package seedu.dengue.model.person;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.range.End;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.Start;

/**
 * An interface for {@link Age} and {@link Date} classes, both of which are continuous.
 */
public interface ContinuousData {
    public boolean equals(Object other);

    /**
     * Generates a range of continuous data.
     * @param start The start of the range.
     * @param end The end of the range.
     * @return A {@code Range<ContinuousData>}.
     */
    public static <R extends ContinuousData> Range<R> generateRange(Start<R> start, End<R> end)
            throws ParseException {
        return Range.<R>of(start, end);
    }
}
