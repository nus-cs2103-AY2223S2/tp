package seedu.loyaltylift.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.loyaltylift.commons.util.StringUtil;

/**
 * Tests that an {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class OrderNameContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order customer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(customer.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((OrderNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
