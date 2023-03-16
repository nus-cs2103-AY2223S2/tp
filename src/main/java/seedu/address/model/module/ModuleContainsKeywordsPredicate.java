package seedu.address.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code Code} matches user's input.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final ModuleCode moduleCode;

    public ModuleContainsKeywordsPredicate(ModuleCode code) {
        this.moduleCode = code;
    }

    public ModuleContainsKeywordsPredicate(String code) {
        this.moduleCode = new ModuleCode(code);
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return this.moduleCode.equals(module.getCode());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleContainsKeywordsPredicate) other).moduleCode)); // state check
    }
}
