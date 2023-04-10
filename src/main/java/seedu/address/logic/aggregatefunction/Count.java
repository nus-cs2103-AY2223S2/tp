package seedu.address.logic.aggregatefunction;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An {@code AggregateFunction} to count the number of items in a {@code list}.
 *
 * @param <T> Type of item in the list.
 */
public class Count<T> extends AggregateFunction {

    protected final List<T> list;
    protected final String description;
    protected Predicate<T> predicate;

    /**
     * Constructs the Count {@code Aggregate function}.
     *
     * @param list List to operate on.
     * @param description Description of the count.
     */
    public Count(List<T> list, String description) {
        this.list = list;
        this.description = description;
        this.predicate = (item -> true);
    }

    /**
     * Specifies a predicate for which the items are included in the count.
     *
     * @param predicate Predicate to filter items.
     * @return Updated Count aggregate function.
     */
    public Count<T> with(Predicate<T> predicate) {
        this.predicate = predicate;
        return this;
    }

    protected Stream<T> streamFilteredList() {
        return list.stream().filter(predicate);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getResult() {
        return Integer.toString(streamFilteredList().toArray().length);
    }
}
