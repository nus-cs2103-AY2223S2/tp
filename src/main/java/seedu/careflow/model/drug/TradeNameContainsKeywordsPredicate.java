package seedu.careflow.model.drug;

import java.util.List;
import java.util.function.Predicate;

import seedu.careflow.commons.util.StringUtil;

/**
 * Tests that a {@code Drug}'s {@code TradeName} matches any of the keywords given.
 */
public class TradeNameContainsKeywordsPredicate implements Predicate<Drug> {
    private final List<String> keywords;

    public TradeNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Drug drug) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(drug.getTradeName().tradeName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other
                instanceof seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals((
                        (seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate)
                                other).keywords)); // state check
    }
}
