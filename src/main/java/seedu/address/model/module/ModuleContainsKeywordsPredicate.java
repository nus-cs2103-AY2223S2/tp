package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code code} or {@code name} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return keywords.stream()
                .anyMatch(keyword -> isMatched(module.getCode().code, keyword)
                || isMatched(module.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleContainsKeywordsPredicate) other).keywords)); // state check
    }

    private boolean isMatched(String moduleContent, String keyword) {
        return StringUtil.containsWordIgnoreCase(moduleContent,
                StringUtil.joinSentenceToWord(keyword));
    }

}
