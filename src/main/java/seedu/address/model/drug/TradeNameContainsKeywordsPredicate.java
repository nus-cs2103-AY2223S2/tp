package seedu.address.model.drug;

import seedu.address.commons.util.StringUtil;
import java.util.List;
import java.util.function.Predicate;

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
                || (other instanceof seedu.address.model.drug.TradeNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((seedu.address.model.drug.TradeNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
