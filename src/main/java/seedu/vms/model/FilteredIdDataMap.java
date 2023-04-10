package seedu.vms.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.ObservableMap;


/**
 * An extension of {@link FilteredMapView} to provide a filtered view for Id
 * maps.
 *
 * @param <T> - the type of value being stored.
 */
public class FilteredIdDataMap<T> extends FilteredMapView<Integer, IdData<T>> {
    /**
     * Constructs a {@code FilteredIdDataMap} that is bound to the given map.
     *
     * @param source - the map to bind to.
     */
    public FilteredIdDataMap(ObservableMap<Integer, IdData<T>> source) {
        super(source);
    }


    /**
     * Applies a filter.
     *
     * @param filter - the filter function.
     */
    public void filter(Predicate<T> filter) {
        Objects.requireNonNull(filter);
        filter(List.of(filter));
    }


    /**
     * Applies a collection of filters.
     *
     * @param filters - the list of filters to use.
     */
    public void filter(Collection<Predicate<T>> filters) {
        Objects.requireNonNull(filters);
        setFilters(filters.stream()
                .map(this::convertFilter)
                .collect(Collectors.toList()));
    }


    private Predicate<IdData<T>> convertFilter(Predicate<T> filter) {
        return idData -> filter.test(idData.getValue());
    }
}
