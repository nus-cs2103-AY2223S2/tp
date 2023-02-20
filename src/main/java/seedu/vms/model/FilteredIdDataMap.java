package seedu.vms.model;

import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;


public class FilteredIdDataMap<T> {
    private final ObservableMap<Integer, IdData<T>> source;
    private final ObservableMap<Integer, IdData<T>> internalMap;
    private final ObservableMap<Integer, IdData<T>> internalUnmodifiableMap;

    private Predicate<T> predicate = input -> true;


    public FilteredIdDataMap(ObservableMap<Integer, IdData<T>> source) {
        this.source = FXCollections.unmodifiableObservableMap(source);
        internalMap = FXCollections.observableHashMap();
        internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);

        source.addListener(this::handleChange);
    }


    private void handleChange(MapChangeListener.Change<? extends Integer, ? extends IdData<T>> change) {
        if (change.wasRemoved()) {
            internalMap.remove(change.getKey());
        } else if (predicate.test(change.getValueAdded().getValue())) {
            internalMap.put(change.getKey(), change.getValueAdded());
        }
    }


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


    public ObservableMap<Integer, IdData<T>> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }
}
