package seedu.address.logic.aggregatefunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * An {@code AggregateFunction} to count the maximum number of items per group in a {@code list}.
 *
 * @param <T> Type of item in the list.
 * @param <R> Type of item returned by groupBy mapper.
 */
public class MaxCount<T, R> extends GroupedCount<T, R> {

    /**
     * Constructs a MaxCount {@code AggregateFunction}.
     *
     * @param list List to operate on.
     * @param description Description of the count.
     * @param mapper Mapper to group items by.
     */
    public MaxCount(List<T> list, String description, Function<T, R> mapper) {
        super(list, description, mapper);
    }

    @Override
    protected int summariseCounts(HashMap<R, ArrayList<T>> counts) {
        return getGroups().values().stream().map(group -> group.size()).reduce(0, Integer::max);
    }
}
