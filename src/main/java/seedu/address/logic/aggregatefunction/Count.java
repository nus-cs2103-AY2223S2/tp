package seedu.address.logic.aggregatefunction;

import java.util.List;
import java.util.function.Predicate;

/**
 * An {@code AggregateFunction} to count the number of items in a {@code list}.
 *
 * @param <T> Type of item in the list.
 */
public class Count<T> extends AggregateFunction {

    private final List<T> list;
    private final String description;
    private Predicate<T> predicate;

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
     * @return Updated count aggregate function.
     */
    public Count<T> with(Predicate<T> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getResult() {
        return Integer.toString(list.stream().filter(predicate).toArray().length);
    }
}
