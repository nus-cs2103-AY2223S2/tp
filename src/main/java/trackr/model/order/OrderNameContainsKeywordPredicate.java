package trackr.model.order;

import java.util.List;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code OrderName} matches any of the keywords given.
 */
public class OrderNameContainsKeywordPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderNameContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
              .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getOrderName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderNameContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((OrderNameContainsKeywordPredicate) other).keywords)); // state check
    }
}
