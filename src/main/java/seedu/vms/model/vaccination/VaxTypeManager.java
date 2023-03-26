package seedu.vms.model.vaccination;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.commons.exceptions.LimitExceededException;


/**
 * A manager to handle {@code VaxType}.
 *
 * <p>The uniqueness of {@code VaxType} names are maintained.
 */
public class VaxTypeManager {
    public static final int DEFAULT_LIMIT = 30;

    private static final String ERROR_FORMAT_LIMIT_EXCEEDED = "Limit of %d exceeded";

    private final int limit;

    private final ObservableMap<String, VaxType> typeMap;
    private final ObservableMap<String, VaxType> unmodifiableTypeMap;


    /**
     * Constructs a {@code VaxTypeManager} with the default limit.
     */
    public VaxTypeManager() {
        this(DEFAULT_LIMIT);
    }


    /**
     * Constructs a {@code VaxTypeManager}.
     *
     * @param limit - the size limit of this {@code VaxTypeManager}.
     */
    public VaxTypeManager(int limit) {
        this.limit = limit;
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }


    /**
     * Resets the data of this {@code VaxTypeManager} to the specified manager.
     *
     * @param manager - the state of the manager to reset to.
     * @throws LimitExceededException if the size of the given manager is
     *      larger than the set limit of this manager.
     */
    public void resetData(VaxTypeManager manager) {
        if (manager.size() > limit) {
            throw new LimitExceededException(String.format(ERROR_FORMAT_LIMIT_EXCEEDED,
                    limit));
        }
        typeMap.clear();
        typeMap.putAll(manager.typeMap);
    }


    /**
     * Adds the specified {@code VaxType}. If there is another {@code VaxType}
     * with the same name as the specified, that {@code VaxType} is replaced.
     *
     * @param vaxType - the {@code VaxType} to add.
     * @throws LimitExceededException if the set limit will be exceeded after
     *      this addition.
     */
    public void add(VaxType vaxType) {
        if (typeMap.size() >= limit) {
            throw new LimitExceededException(String.format(ERROR_FORMAT_LIMIT_EXCEEDED,
                    limit));
        }
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
        return Optional.ofNullable(typeMap.remove(name));
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
     * Returns the number of {@code VaxType} stored within this manager.
     */
    public int size() {
        return typeMap.size();
    }


    /**
     * Returns if the manager is empty.
     *
     * @return {@code true} if the manager contains zero {@code VaxType} and
     *      {@code false} otherwise.
     */
    public boolean isEmpty() {
        return typeMap.isEmpty();
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
