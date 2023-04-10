package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * Wraps all data at the tracker level.<p>
 * Duplicate modules are not allowed (by {@code Module#isSameModule(ReadOnlyModule)} comparison)
 */
public class Tracker implements ReadOnlyTracker {
    private final UniqueModuleList modules;

    /**
     * Constructs a Tracker.
     */
    public Tracker() {
        modules = new UniqueModuleList();
    }

    /**
     * Constructs a Tracker using the data in {@code toBeCopied}.
     *
     * @param toBeCopied The tracker whose data is to be copied to this tracker.
     */
    public Tracker(ReadOnlyTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the module list with {@code modules}.
     *
     * @param modules The modules that will replace the contents of the module list. It must not contain duplicates.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Clears the exisitng data of this {@code Tracker}
     */
    public void clear() {
        setModules(List.of());
    }

    /**
     * Resets the existing data of this {@code Tracker} with {@code newData}.
     *
     * @param newData The tracker whose data is to be copied to this tracker.
     */
    public void resetData(ReadOnlyTracker newData) {
        requireNonNull(newData);

        setModules(newData
                .getModuleList()
                .stream()
                .map((d) -> (Module) d)
                .collect(Collectors.toList()));
    }

    //// module-level operations

    @Override
    public ObservableList<? extends ReadOnlyModule> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);

        return getModuleList()
                .stream()
                .filter((m) -> m.getCode().equals(moduleCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean hasModule(ReadOnlyModule module) {
        requireNonNull(module);
        return modules.contains((Module) module);
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return getModule(moduleCode) != null;
    }

    /**
     * Adds a module to the tracker.
     *
     * @param module The module to be added. It must not already exist in the tracker.
     * @throws DuplicateModuleException Indicates that {@code module} already exist in the tracker.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     *
     * @param target The module to be replaced. It must exist in the tracker.
     * @param editedModule The module that will replace. It must not be the same as another existing module in
     *                     the tracker.
     * @throws ModuleNotFoundException Indicates that {@code target} does not exist in the tracker.
     * @throws DuplicateModuleException Indicates that {@code editedModule} is the same as another existing
     *                                  module in the tracker.
     */
    public void setModule(ReadOnlyModule target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        modules.setModule((Module) target, editedModule);
    }

    /**
     * Removes the given module {@code key} from this {@code Tracker}.
     *
     * @param key The module to remove from this tracker. It must exist in the tracker.
     * @throws ModuleNotFoundException Indicates that the module does not exist in the tracker.
     */
    public void removeModule(ReadOnlyModule key) {
        modules.remove((Module) key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tracker // instanceof handles nulls
                && modules.equals(((Tracker) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
