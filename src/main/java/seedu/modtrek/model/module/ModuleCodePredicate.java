package seedu.modtrek.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ModuleCodePredicate implements Predicate<Module> {
    private final Code moduleCode;

    public ModuleCodePredicate(Code moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public boolean test(Module module) {
        return module.getCode().equals(moduleCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodePredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleCodePredicate) other).moduleCode)); // state check
    }

}