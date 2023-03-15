package seedu.address.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code Code} matches user's input.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final ModuleCode code;

    public ModuleContainsKeywordsPredicate(ModuleCode code) {
        this.code = code;
    }

    public ModuleContainsKeywordsPredicate(String code) {
        this.code = new ModuleCode(code);
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return this.code.equals(module.getCode());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && code.equals(((ModuleContainsKeywordsPredicate) other).code)); // state check
    }
}
