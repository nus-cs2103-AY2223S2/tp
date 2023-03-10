package seedu.address.model.module;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code Module#isSameModule(Module)}. As such, adding and updating of
 * modules uses Module#isSameModule(Module) for equality so as to ensure that the module being added or updated is
 * unique in terms of code in the UniqueModuleList. However, the removal of a module uses Module#equals(Object) so
 * as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Module#isSameModule(Module)
 */
public class UniqueModuleList extends UniqueDataList<Module> {

    public UniqueModuleList() {
        super((a, b) -> a.isSameModule(b), DuplicateModuleException::new, ModuleNotFoundException::new);
    }

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    @Override
    public boolean contains(Module toCheck) {
        return super.contains(toCheck);
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    @Override
    public void add(Module toAdd) {
        super.add(toAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module code of {@code editedModule} must not be the same as another existing module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        super.setData(target, editedModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    @Override
    public void remove(Module toRemove) {
        super.remove(toRemove);
    }

    public void setModules(UniqueModuleList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        super.setAllData(modules);
    }
}
