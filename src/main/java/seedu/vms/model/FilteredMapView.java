package seedu.vms.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;


/**
 * A map that is a filtered view of its bounded map.
 *
 * @param <K> the key of the map.
 * @param <V> the value of the map.
 */
public class FilteredMapView<K, V> {
    private final ObservableMap<K, V> source;
    private final ObservableMap<K, V> internalMap;
    private final ObservableMap<K, V> unmodifiableMap;

    private final ArrayList<Predicate<V>> filters = new ArrayList<>();


    /**
     * Constructs a {@code FilteredMapView}.
     *
     * @param source - the map to provide a filtered view of.
     */
    public FilteredMapView(ObservableMap<K, V> source) {
        this.source = FXCollections.unmodifiableObservableMap(source);
        this.source.addListener(this::handleChange);
        internalMap = FXCollections.observableHashMap();
        unmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);

        filterSource();
    }


    private void handleChange(MapChangeListener.Change<? extends K, ? extends V> change) {
        if (change.wasRemoved()) {
            internalMap.remove(change.getKey());
        }
        if (change.wasAdded() && test(change.getValueAdded())) {
            internalMap.put(change.getKey(), change.getValueAdded());
        }
    }


    private boolean test(V value) {
        for (Predicate<V> filter : filters) {
            if (!filter.test(value)) {
                return false;
            }
        }
        return true;
    }


    private void filterSource() {
        internalMap.clear();
        for (K key : source.keySet()) {
            V value = source.get(key);
            if (test(value)) {
                internalMap.put(key, value);
            }
        }
    }


    /**
     * Sets the filters to use to the single specified filter.
     *
     * @param filter - the filter to use.
     */
    public void setFilters(Predicate<V> filter) {
        setFilters(List.of(filter));
    }


    /**
     * Sets the filters to use.
     *
     * @param filters - a collection of filters to use.
     */
    public void setFilters(Collection<Predicate<V>> filters) {
        this.filters.clear();
        this.filters.addAll(filters);
        filterSource();
    }


    /**
     * Returns an unmodifiable map view of the filtered map.
     *
     * @return an unmodifiable map view of the filtered map.
     */
    public ObservableMap<K, V> asUnmodifiableObservableMap() {
        return unmodifiableMap;
    }
}
