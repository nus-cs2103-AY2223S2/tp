package seedu.dengue.model.range;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

import java.util.Optional;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.ContinuousData;

/**
 * Represents a range which can be used to signify age ranges, or date ranges to be used in predicates
 * to filter out people in the person list in the Dengue Hotspot Tracker.
 */
public class Range<T> {

    public static final String MESSAGE_INVALID_RANGE = "Invalid range!";

    public final Start<T> start;
    public final End<T> end;

    /**
     * Constructs a {@code Range}.
     * Start and End should form a valid range, i.e. Start < End.
     * @param start A Start.
     * @param end An End.
     */
    private Range(Start<T> start, End<T> end) {
        requireNonNull(start);
        requireNonNull(end);
        checkArgument(isValidRange(start, end), MESSAGE_INVALID_RANGE);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a {@code Range} of {@link ContinuousData} values.
     * This ensures that only ranges of continuous data such as dates and ages can be created.
     * @param start Start of the {@link ContinuousData} range.
     * @param end End of the {@link ContinuousData} range.
     * @param <R> An implementation of {@link ContinuousData} such as {@code Date}.
     * @return A range.
     */
    public static <R extends ContinuousData> Range<R> of(Start<R> start, End<R> end) throws ParseException {
        return new Range<>(start, end);
    }

    public Start<T> getStart() {
        return start;
    }

    public End<T> getEnd() {
        return end;
    }

    /**
     * Checks for whether the range is empty.
     * @return true is the range is empty, false otherwise.
     */
    public boolean isRangeEmpty() {
        Optional<T> optionalStart = this.getStart().get();
        Optional<T> optionalEnd = this.getEnd().get();
        return optionalStart.isEmpty() && optionalEnd.isEmpty();
    }

    /**
     * Checks for whether the {@code start} is after or equals to the {@code end} which constitutes an invalid range
     * @param start Start of the {@link ContinuousData} range.
     * @param end End of the {@link ContinuousData} range.
     * @return true if it is a valid range, false otherwise.
     */

    public boolean isValidRange(Start<T> start, End<T> end) {
        boolean isStartValid = start.isValidStart(end);
        boolean isEndValid = end.isValidEnd(start);
        return isStartValid && isEndValid;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Range // instanceof handles nulls
                && start.equals(((Range<T>) other).start)
                && end.equals(((Range<T>) other).end));
    }

}
