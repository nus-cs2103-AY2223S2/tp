package trackr.model.menu;

import java.util.List;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;
import trackr.model.item.Item;

/**
 * Tests that a {@code MenuItem}'s {@code ItemName} matches any of the keywords given.
 */
//@@author changgittyhub-reused
public class ItemNameContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public ItemNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        if (!(item instanceof MenuItem)) {
            return false;
        }

        MenuItem menuItem = (MenuItem) item;

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(menuItem.getItemName().getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItemNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
