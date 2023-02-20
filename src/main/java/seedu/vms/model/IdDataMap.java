package seedu.vms.model;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


public class IdDataMap<T> {
    private final ObservableMap<Integer, IdData<T>> internalMap;
    private final ObservableMap<Integer, IdData<T>> internalUnmodifiableMap;

    private int nextId = 0;


    public IdDataMap() {
        internalMap = FXCollections.observableHashMap();
        internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);
    }


    public void add(T value) {
        Objects.requireNonNull(value);
        IdData<T> data = new IdData<>(nextId, value);
        add(data);
        nextId++;
    }


    public void add(IdData<T> data) {
        Objects.requireNonNull(data);
        internalMap.put(data.getId(), data);
    }


    public void set(int id, T value) {
        if (!internalMap.containsKey(id)) {
            throw new NoSuchElementException(String.format("ID [%d] not found", id));
        }
        IdData<T> data = new IdData<>(id, value);
        internalMap.put(id, data);
    }


    public IdData<T> remove(int id) {
        if (!internalMap.containsKey(id)) {
            throw new NoSuchElementException(String.format("ID [%d] not found", id));
        }
        return internalMap.remove(id);
    }


    public boolean containts(int id) {
        return internalMap.containsKey(id);
    }


    public void setDatas(Collection<IdData<T>> datas) {
        internalMap.clear();
        nextId = 0;
        for (IdData<T> data : datas) {
            int id = data.getId();
            if (id >= nextId) {
                nextId = id + 1;
            }
            internalMap.put(id, data);
        }
    }


    public void setValues(Collection<T> values) {
        internalMap.clear();
        nextId = 0;
        for (T value : values) {
            add(value);
        }
    }


    public ObservableMap<Integer, IdData<T>> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }
}
