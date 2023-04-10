package seedu.vms.model.vaccination;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;


/**
 * A manager to handle {@code VaxType}.
 *
 * <p>The uniqueness of {@code VaxType} names are maintained.
 */
public class VaxTypeManager implements ReadOnlyVaxTypeManage {
    public static final int DEFAULT_LIMIT = 30;

    private static final String ERROR_FORMAT_DUPLICATE_VACCINATION = "Vaccination %s already exist";
    private static final String ERROR_FORMAT_NONEXISTENT_VACCINATION = "Vaccination %s does not exist";

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
            throw new LimitExceededException(String.format(Messages.FORMAT_LIMIT_EX, limit));
        }
        typeMap.clear();
        typeMap.putAll(manager.typeMap);
    }


    /**
     * Adds the specified {@code VaxType}.
     *
     * @param vaxType - the {@code VaxType} to add.
     * @throws IllegalValueException if the a vaccination with the same name
     *      already exist.
     * @throws LimitExceededException if the set limit will be exceeded after
     *      this addition.
     */
    public VaxType add(VaxType vaxType) throws IllegalValueException {
        if (typeMap.containsKey(vaxType.getName())) {
            throw new IllegalValueException(String.format(ERROR_FORMAT_DUPLICATE_VACCINATION,
                    vaxType.getName()));
        }
        if (typeMap.size() >= limit) {
            throw new LimitExceededException(String.format(Messages.FORMAT_LIMIT_EX, limit));
        }
        typeMap.put(vaxType.getName(), vaxType);
        return vaxType;
    }


    /**
     * Replaces the {@code VaxType} with the specified name with the given
     * {@code VaxType}.
     *
     * @return a {@code ValueChange} that describes the change that has
     *      occurred.
     * @throws IllegalValueException if the {@code VaxType} with the specified
     *      name cannot be found or if the given {@code VaxType} has the same
     *      name as an already existing {@code VaxType} that is not the
     *      specified name.
     */
    public ValueChange<VaxType> set(String name, VaxType newValue) throws IllegalValueException {
        if (!typeMap.containsKey(name)) {
            throw new IllegalValueException(String.format(ERROR_FORMAT_NONEXISTENT_VACCINATION,
                    name));
        }
        if (!(name.equals(newValue.getName())) && typeMap.containsKey(newValue.getName())) {
            throw new IllegalValueException(String.format(ERROR_FORMAT_DUPLICATE_VACCINATION,
                    newValue.getName()));
        }
        VaxType oldValue = typeMap.remove(name);
        typeMap.put(newValue.getName(), newValue);
        return new ValueChange<>(oldValue, newValue);
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


    @Override
    public Optional<VaxType> get(String name) {
        return Optional.ofNullable(typeMap.get(name));
    }


    @Override
    public boolean contains(String name) {
        return typeMap.containsKey(name);
    }


    @Override
    public int size() {
        return typeMap.size();
    }


    @Override
    public boolean isEmpty() {
        return typeMap.isEmpty();
    }


    @Override
    public ObservableMap<String, VaxType> asUnmodifiableObservableMap() {
        return unmodifiableTypeMap;
    }
}
