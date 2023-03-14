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

/**
 * Wraps all data at the tracker level
 * Duplicate modules are not allowed (by .isSameModule comparison)
 */
public class Tracker implements ReadOnlyTracker {
    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
    }

    public Tracker() {}

    /**
     * Creates a Tracker using the Modules in the {@code toBeCopied}
     */
    public Tracker(ReadOnlyTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code Tracker} with {@code newData}.
     */
    public void resetData(ReadOnlyTracker newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList().stream().map((d) -> (Module) d).collect(Collectors.toList()));
    }

    //// module-level operations

    /**
     * Returns true if a module with the same code as {@code module} exists in the tracker.
     */
    public boolean hasModule(ReadOnlyModule module) {
        requireNonNull(module);
        return modules.contains((Module) module);
    }

    /**
     * Adds a module to the tracker.
     * The module must not already exist in the tracker.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the tracker.
     * The module of {@code editedModule} must not be the same as another existing module in the tracker.
     */
    public void setModule(ReadOnlyModule target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        modules.setModule((Module) target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code Tracker}.
     * {@code key} must exist in the tracker.
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
    public ObservableList<? extends ReadOnlyModule> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode code) {
        return getModuleList()
                .stream()
                .filter((m) -> m.getCode().equals(code))
                .findFirst()
                .orElse(null);
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
