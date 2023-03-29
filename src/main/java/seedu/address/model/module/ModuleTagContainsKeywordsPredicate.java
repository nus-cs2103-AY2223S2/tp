package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code tags} matches any of the keywords given.
 */
public class ModuleTagContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final List<String> keywords;

    public ModuleTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                        StringUtil.joinTagsAsString(module.getTags()),
                            StringUtil.joinSentenceToWord(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleTagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
