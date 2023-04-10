package trackr.model.person;

import java.util.List;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;
import trackr.model.item.Item;

/**
 * Tests that a {@code Suppliers}'s {@code Name} matches any of the keywords given.
 */
//@@author liumc-sg-reused
public class PersonNameContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public PersonNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        if (!(item instanceof Supplier)) {
            return false;
        }

        Supplier supplier = (Supplier) item;

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(supplier.getPersonName().getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
