package seedu.vms.model;

import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;


/**
 * An encapsulation of an {@code ObservableMap} of ID - data pairs to provide
 * a filtered view of a binded map.
 *
 * @param <T> - the type of value being stored.
 */
public class FilteredIdDataMap<T> {
    private final ObservableMap<Integer, IdData<T>> source;
    private final ObservableMap<Integer, IdData<T>> internalMap;
    private final ObservableMap<Integer, IdData<T>> internalUnmodifiableMap;

    private Predicate<T> predicate = input -> true;


    /**
     * Constructs a {@code FilteredIdDataMap} that is bound to the given map.
     *
     * @param source - the map to bind to.
     */
    public FilteredIdDataMap(ObservableMap<Integer, IdData<T>> source) {
        this.source = FXCollections.unmodifiableObservableMap(source);
        internalMap = FXCollections.observableHashMap();
        internalMap.putAll(source);
        internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);

        source.addListener(this::handleChange);
    }


    private void handleChange(MapChangeListener.Change<? extends Integer, ? extends IdData<T>> change) {
        if (change.wasRemoved()) {
            internalMap.remove(change.getKey());
        }
        if (change.wasAdded() && predicate.test(change.getValueAdded().getValue())) {
            internalMap.put(change.getKey(), change.getValueAdded());
        }
    }


    /**
     * Applies a filter.
     *
     * @param predicate - the filter function.
     */
    public void filter(Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        this.predicate = predicate;
        for (IdData<T> data : source.values()) {
            if (predicate.test(data.getValue())) {
                internalMap.put(data.getId(), data);
            } else {
                internalMap.remove(data.getId());
            }
        }
    }


    /**
     * Returns an unmodifiable map view of the filtered map.
     *
     * @return an unmodifiable map view of the filtered map.
     */
    public ObservableMap<Integer, IdData<T>> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }
}
