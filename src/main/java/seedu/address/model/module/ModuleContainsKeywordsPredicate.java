package seedu.address.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code Code} matches user's input.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Module> {
    private final ModuleCode code;

    public ModuleContainsKeywordsPredicate(ModuleCode code) {
        this.code = code;
    }

    @Override
    public boolean test(Module module) {
        return this.code.equals(module.getCode());
    }

}
