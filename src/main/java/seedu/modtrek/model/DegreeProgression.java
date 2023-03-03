package seedu.modtrek.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.UniqueModuleList;

public class DegreeProgression implements ReadOnlyDegreeProgression {

    private final UniqueModuleList modules = new UniqueModuleList();

    public DegreeProgression() {}

    /**
     * Creates an DegreeProgression using the Modules in the {@code toBeCopied}
     */
    public DegreeProgression(ReadOnlyDegreeProgression toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Module list with {@code Modules}.
     * {@code Modules} must not contain duplicate Modules.
     */
    public void setModules(List<Module> Modules) {
        this.modules.setModules(Modules);
    }

    /**
     * Resets the existing data of this {@code DegreeProgression} with {@code newData}.
     */
    public void resetData(ReadOnlyDegreeProgression newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList());
    }

    //// Module-level operations

    /**
     * Returns true if a Module with the same identity as {@code Module} exists in the address book.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a Module to the address book.
     * The Module must not already exist in the address book.
     */
    public void addModule(Module p) {
        modules.add(p);
    }

    /**
     * Replaces the given Module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The Module identity of {@code editedModule} must not be the same as another existing Module in the address book.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code DegreeProgression}.
     * {@code key} must exist in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DegreeProgression // instanceof handles nulls
                && modules.equals(((DegreeProgression) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
