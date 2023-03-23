package seedu.address.logic.aggregatefunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * An {@code AggregateFunction} to group items in a {@code list}.
 *
 * @param <T> Type of item in the list.
 * @param <R> Type of item returned by groupBy mapper.
 */
public abstract class GroupedCount<T, R> extends Count<T> {

    protected Function<T, R> groupByMapper;

    /**
     * Constructs the GroupedCount {@code Aggregate function}.
     * Items mapped to equal {@code R} using mapper belong to the same group.
     *
     * @param list List to operate on.
     * @param description Description of the count.
     * @param mapper Mapper to group items by.
     */
    public GroupedCount(List<T> list, String description, Function<T, R> mapper) {
        super(list, description);
        this.groupByMapper = mapper;
    }

    /**
     * Specifies a mapper to group items in the list by.
     * Items mapped to equal {@code R} belong to the same group.
     *
     * @param mapper Mapper to group items.
     * @return updated GroupCount aggregate function.
     */
    public GroupedCount<T, R> groupBy(Function<T, R> mapper) {
        groupByMapper = mapper;
        return this;
    }

    /**
     * Groups the items in the list using the specified mapper.
     *
     * @return Grouped items.
     */
    protected HashMap<R, ArrayList<T>> getGroups() {
        HashMap<R, ArrayList<T>> groups = new HashMap<>();
        streamFilteredList().forEach(item -> {
            R key = groupByMapper.apply(item);
            ArrayList<T> group = groups.getOrDefault(key, new ArrayList<>());
            group.add(item);
            groups.put(key, group);
        });
        return groups;
    }

    /**
     * Summarises the groups of items with a single number.
     *
     * @param groups
     * @return
     */
    protected abstract int summariseCounts(HashMap<R, ArrayList<T>> groups);

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getResult() {
        return Integer.toString(summariseCounts(getGroups()));
    }
}
