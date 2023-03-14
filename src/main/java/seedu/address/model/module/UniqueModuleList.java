package seedu.address.model.module;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls.<p>
 *
 * A module is considered unique by comparing using {@code Module#isSameModule(Module)}. As such, adding and updating of
 * modules uses {@code Module#isSameModule(Module)} for equality so as to ensure that the module being added or updated
 * is unique in terms of module code in the {@code UniqueModuleList}.<p>
 *
 * However, the removal of a module uses {@code Module#equals(Object)} so as to ensure that the module with exactly the
 * same fields will be removed.<p>
 *
 * Supports a minimal set of list operations.<p>
 *
 * @see Module#isSameModule(Module)
 */
public class UniqueModuleList extends UniqueDataList<Module> {

    /**
     * Constructs a {@code UniqueModuleList}.
     */
    public UniqueModuleList() {
        super((a, b) -> a.isSameModule(b), DuplicateModuleException::new, ModuleNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     *
     * @param toAdd {@inheritDoc}
     * @throws DuplicateModuleException Indicates that {@code toAdd} already exist in the list.
     */
    @Override
    public void add(Module toAdd) {
        super.add(toAdd);
    }

    /**
     * {@inheritDoc}
     *
     * @param toRemove {@inheritDoc}
     * @throws ModuleNotFoundException Indicates that the module does not exist in the list.
     */
    @Override
    public void remove(Module toRemove) {
        super.remove(toRemove);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.<p>
     * {@code target} must exist in the list.<p>
     * {@code editedModule} must not be the same as another existing module in the list.
     *
     * @param target The module to be replaced.
     * @param editedModule The module that will replace.
     * @throws ModuleNotFoundException Indicates that {@code target} does not exist in the list.
     * @throws DuplicateModuleException Indicates that {@code editedModule} is the same as another existing
     *                                  module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        super.setData(target, editedModule);
    }

    /**
     * Replaces the content of this list with {@code replacement}.
     *
     * @param replacement The list containing the modules that will replace.
     */
    public void setModules(UniqueModuleList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code modules}.<p>
     * {@code modules} must not contain duplicate modules.
     *
     * @param modules The list containing the modules that will replace.
     */
    public void setModules(List<Module> modules) {
        super.setAllData(modules);
    }
}
