package seedu.modtrek.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.model.degreedata.DegreeProgressionData;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.UniqueModuleList;

/**
 * The type Degree progression.
 */
public class DegreeProgression implements ReadOnlyDegreeProgression {

    private final UniqueModuleList modules = new UniqueModuleList();

    /**
     * Instantiates a new Degree progression.
     */
    public DegreeProgression() {}

    /**
     * Creates an DegreeProgression using the Modules in the {@code toBeCopied}
     *
     * @param toBeCopied the to be copied
     */
    public DegreeProgression(ReadOnlyDegreeProgression toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Module list with {@code modules}.
     * {@code modules} must not contain duplicate Modules.
     *
     * @param modules the modules
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code DegreeProgression} with {@code newData}.
     *
     * @param newData the new data
     */
    public void resetData(ReadOnlyDegreeProgression newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList());
    }

    //// Module-level operations

    /**
     * Returns true if a Module with the same identity as {@code Module} exists in the address book.
     *
     * @param module the module
     * @return the boolean
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a Module to the address book.
     * The Module must not already exist in the address book.
     *
     * @param p the p
     */
    public void addModule(Module p) {
        modules.add(p);
    }

    /**
     * Replaces the given Module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The Module identity of {@code editedModule} must not be the same as another existing Module in the address book.
     *
     * @param target       the target
     * @param editedModule the edited module
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code DegreeProgression}.
     * {@code key} must exist in the address book.
     *
     * @param key the key
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    public TreeMap<Object, ObservableList<Module>> getModuleGroups() {
        return modules.getModuleGroups();
    }

    public TreeMap<Object, ObservableList<Module>> sortModuleGroups(SortCommand.Sort sort) {
        return modules.sortByObject(sort);
    }

    @Override
    public String getSort() {
        return modules.getSort();
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
    public DegreeProgressionData getProgressionData() {
        return DegreeProgressionData.generate(modules);
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
