package seedu.address.model.expense;

import org.junit.jupiter.api.Test;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ExpenseContainsKeywordsPredicate firstPredicate = new ExpenseContainsKeywordsPredicate(firstPredicateKeywordList);
        ExpenseContainsKeywordsPredicate secondPredicate = new ExpenseContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseContainsKeywordsPredicate firstPredicateCopy = new ExpenseContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseContainsKeywords_returnsTrue() {
        // One keyword
        ExpenseContainsKeywordsPredicate predicate =
                new ExpenseContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(
                new Expense("Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));

        // Multiple keywords
        predicate = new ExpenseContainsKeywordsPredicate(Arrays.asList("Fuji", "Apple"));
        assertTrue(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));

        // Only one matching keyword
        predicate = new ExpenseContainsKeywordsPredicate(Arrays.asList("Orange", "Apple"));
        assertTrue(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));

        // Mixed-case keywords
        predicate = new ExpenseContainsKeywordsPredicate(Arrays.asList("fUjI", "aPPle"));
        assertTrue(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ExpenseContainsKeywordsPredicate predicate = new ExpenseContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));

        // Non-matching keyword
        predicate = new ExpenseContainsKeywordsPredicate(Arrays.asList("Orange"));
        assertFalse(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));

        // Keywords match price and category, but does not match name
        predicate = new ExpenseContainsKeywordsPredicate(Arrays.asList("1.50", "Miscellaneous"));
        assertFalse(predicate.test(
                new Expense("Fuji Apple", 1.50, LocalDate.now(), new MiscellaneousCategory())));
    }
}
