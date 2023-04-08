package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.testutil.MenuItemBuilder;

public class ItemNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ItemNameContainsKeywordsPredicate firstPredicate =
                new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ItemNameContainsKeywordsPredicate firstPredicateCopy =
                new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList);

        assertEquals(firstPredicate, firstPredicateCopy);

        // different values -> returns false
        ItemNameContainsKeywordsPredicate secondPredicate =
                new ItemNameContainsKeywordsPredicate(secondPredicateKeywordList);
        assertNotEquals(firstPredicate, secondPredicate);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        MenuItem menuItem = new MenuItemBuilder().withItemName("Chocolate Cookie").build();

        // One keyword
        ItemNameContainsKeywordsPredicate predicate =
                new ItemNameContainsKeywordsPredicate(Collections.singletonList("Chocolate"));
        assertTrue(predicate.test(menuItem));

        // Multiple keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Chocolate", "Cookie"));
        assertTrue(predicate.test(menuItem));

        // Only one matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Chocolate", "Vanilla"));
        assertTrue(predicate.test(menuItem));

        // Mixed-case keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("ChoCoLate", "COOkieS"));
        assertTrue(predicate.test(menuItem));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemNameContainsKeywordsPredicate predicate =
                new ItemNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MenuItemBuilder().withItemName("Chocolate Cookie").build()));

        // Non-matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(List.of("Vanilla"));
        assertFalse(predicate.test(new MenuItemBuilder().withItemName("Chocolate Cookie").build()));

        // null
        assertFalse(predicate.test(null));
    }

}
