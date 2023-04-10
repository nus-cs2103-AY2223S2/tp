package seedu.vms.model.vaccination;

import java.util.Optional;

import javafx.collections.ObservableMap;


/**
 * Represents a vax type manager that supports only read functionalities.
 */
public interface ReadOnlyVaxTypeManage {
    /**
     * Returns the {@code VaxType} mapped to the specified name wrapped in an
     * {@code Optional}. If there are no mappings to the specified name,
     * {@code Optional.empty} is returned instead.
     *
     * @param name - the name of the {@code VaxType} to get.
     * @return the {@code VaxType} mapped to the specified name wrapped in an
     *      {@code Optional}.
     */
    public Optional<VaxType> get(String name);


    /**
     * Returns if the manager contains a mapping to the specified name.
     *
     * @param name - the name of the {@code VaxType} to check.
     * @return {@code true} if the manager contains a mapping to the
     *      specified name and {@code false} otherwise.
     */
    public boolean contains(String name);


    /**
     * Returns the number of mappings the manager has.
     *
     * @return the number of mappings the manager has.
     */
    public int size();


    /**
     * Returns if this manager is empty.
     *
     * @returns {@code true} if the manager is empty and {@code false}
     *      otherwise.
     */
    public boolean isEmpty();


    /**
     * Returns an unmodifiable map view of this manager.
     *
     * @return an unmodifiable map view of this manager.
     */
    public ObservableMap<String, VaxType> asUnmodifiableObservableMap();
}
