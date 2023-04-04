package trackr.model.menu;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;
import trackr.model.item.Item;

/**
 * Tests that a {@code Suppliers}'s {@code Name} matches any of the keywords given.
 */
public class ItemNameContainsKeywordsPredicate extends MenuItemDescriptor implements Predicate<Item> {
    private List<String> itemNameKeywords;

    public ItemNameContainsKeywordsPredicate() {
        super();
    }

    /**
     * Copy constructor.
     */
    public ItemNameContainsKeywordsPredicate(ItemNameContainsKeywordsPredicate toCopy) {
        setItemNameKeywords(toCopy.itemNameKeywords);
    }

    public void setItemNameKeywords(List<String> itemNameKeywords) {
        this.itemNameKeywords = itemNameKeywords;
    }

    public Optional<List<String>> getItemNameKeywords() {
        return Optional.ofNullable(itemNameKeywords);
    }

    public boolean isAnyFieldPresent() {
        return isAnyFieldNonNull() || itemNameKeywords != null;
    }

    @Override
    public boolean test(Item item) {
        if (!(item instanceof Item)) {
            return false;
        }

        MenuItem menuItem = (MenuItem) item;

        boolean isItemNameMatch;

        if (itemNameKeywords != null) {
            isItemNameMatch = itemNameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(menuItem.getItemName().getName(), keyword));
        } else {
            // Default value since no name keyword is set
            isItemNameMatch = true;
        }

        return isItemNameMatch;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemNameContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        ItemNameContainsKeywordsPredicate predicate = (ItemNameContainsKeywordsPredicate) other;

        return getItemNameKeywords().equals(predicate.getItemNameKeywords());
    }
}
