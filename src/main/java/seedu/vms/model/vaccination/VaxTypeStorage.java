package seedu.vms.model.vaccination;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


/**
 * A storage map to store {@code VaxType}.
 *
 * <p>The uniqueness of {@code VaxType} names are maintained.
 */
public class VaxTypeStorage {
    private final ObservableMap<String, VaxType> typeMap;
    private final ObservableMap<String, VaxType> unmodifiableTypeMap;


    /**
     * Constructs an empty {@code VaxTypeStorage}.
     */
    public VaxTypeStorage() {
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }


    /**
     * Adds the specified {@code VaxType}. If there is another {@code VaxType}
     * with the same name as the specified, that {@code VaxType} is replaced.
     *
     * @param vaxType - the {@code VaxType} to add.
     */
    public void add(VaxType vaxType) {
        typeMap.put(vaxType.getName(), vaxType);
    }


    /**
     * Returns the {@code VaxType} mapped to the specified name, wrapped in an
     * {@code Optional}. If there are no mappings to the specified name,
     * {@code Optional.empty} is returned instead.
     *
     * @param name - the name of the {@code VaxType} to retrieve.
     * @return the {@code VaxType} mapped to the specified name, wrapped in an
     *      {@code Optional}.
     */
    public Optional<VaxType> get(String name) {
        return Optional.ofNullable(typeMap.get(name));
    }


    /**
     * Removes the {@code VaxType} with the specified name. The {@code VaxType}
     * removed is returned and wrapped in an {@code Optional}. If there are no
     * mappings, to the specified name, nothing is done and
     * {@code Optional.empty} is returned.
     *
     * @param name - the name of the {@code VaxType} to remove.
     * @return the {@code VaxType} removed wrapped in an {@code Optional}.
     */
    public Optional<VaxType> remove(String name) {
        return Optional.ofNullable(typeMap.get(name));
    }


    /**
     * Returns if this storage map contains a mapping to the specified name.
     *
     * @return {@code true} if there is a {@code VaxType} mapped to the
     *      specified name and {@code false} otherwise.
     */
    public boolean contains(String name) {
        return typeMap.containsKey(name);
    }


    /**
     * Returns an unmodifiable map view of this storage.
     *
     * @return an unmodifiable map view ot this storage.
     */
    public ObservableMap<String, VaxType> asUnmodifiableObservableMap() {
        return unmodifiableTypeMap;
    }
}
