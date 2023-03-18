package seedu.address.logic.aggregatefunction;

import java.util.List;

/**
 * An {@code AggregateFunction} to count the number of items in a {@code list}.
 *
 * @param <T> Type of item in the list.
 */
public class Count<T> extends AggregateFunction {

    public static final String DESCRIPTION = "%1$s count";
    private final List<T> list;
    private final Class<T> itemClass;

    /**
     * Constructs the Count {@code Aggregate function}.
     *
     * @param list List to operate on.
     * @param itemClass Class of item in the list.
     */
    public Count(List<T> list, Class<T> itemClass) {
        this.list = list;
        this.itemClass = itemClass;
    }

    @Override
    public String getDescription() {
        return String.format(DESCRIPTION, itemClass.getSimpleName());
    }

    @Override
    public String getResult() {
        return Integer.toString(list.size());
    }
}
