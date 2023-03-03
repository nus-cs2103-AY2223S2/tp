package seedu.vms.model.vaccination;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


public class VaxTypeStorage {
    private final ObservableMap<String, VaxType> typeMap;
    private final ObservableMap<String, VaxType> unmodifiableTypeMap;


    public VaxTypeStorage() {
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }


    public void add(VaxType vaxType) {
        typeMap.put(vaxType.getName(), vaxType);
    }


    public Optional<VaxType> get(String name) {
        return Optional.ofNullable(typeMap.get(name));
    }


    public Optional<VaxType> remove(String name) {
        return Optional.ofNullable(typeMap.get(name));
    }


    public boolean contains(String name) {
        return typeMap.containsKey(name);
    }


    public ObservableMap<String, VaxType> asUnmodifiableObservableMap() {
        return unmodifiableTypeMap;
    }
}
