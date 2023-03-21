package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.DrugBuilder;

public class TradeNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TradeNameContainsKeywordsPredicate firstPredicate =
                new TradeNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TradeNameContainsKeywordsPredicate secondPredicate =
                new TradeNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TradeNameContainsKeywordsPredicate firstPredicateCopy = new TradeNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tradeNameContainsKeywords_returnsTrue() {
        // One keyword
        TradeNameContainsKeywordsPredicate predicate = new TradeNameContainsKeywordsPredicate(Collections
                .singletonList("Antirheumatics"));
        assertTrue(predicate.test(new DrugBuilder().withTradeName("Antirheumatics").build()));

        // Multiple keywords
        predicate = new TradeNameContainsKeywordsPredicate(Arrays.asList("Antirheumatics", "Daxbia"));
        assertTrue(predicate.test(new DrugBuilder().withTradeName("Antirheumatics Daxbia").build()));

        // Only one matching keyword
        predicate = new TradeNameContainsKeywordsPredicate(Arrays.asList("Daxbia", "Panixine"));
        assertTrue(predicate.test(new DrugBuilder().withTradeName("Antirheumatics Panixine").build()));

        // Mixed-case keywords
        predicate = new TradeNameContainsKeywordsPredicate(Arrays.asList("anTirHeumatics", "daXbIa"));
        assertTrue(predicate.test(new DrugBuilder().withTradeName("Antirheumatics Daxbia").build()));
    }

    @Test
    public void test_tradeNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TradeNameContainsKeywordsPredicate predicate = new TradeNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DrugBuilder().withTradeName("Antirheumatics").build()));

        // Non-matching keyword
        predicate = new TradeNameContainsKeywordsPredicate(Arrays.asList("Panixine"));
        assertFalse(predicate.test(new DrugBuilder().withTradeName("Antirheumatics Daxbia").build()));

        // Keywords match active ingredient, direction, purpose, side effect and storage count, but does not match name
        predicate = new TradeNameContainsKeywordsPredicate(Arrays.asList("amoxicillin", "Taken", "by", "mouth",
                "treat", "pain", "allergic", "reaction", "123"));
        assertFalse(predicate.test(new DrugBuilder().withTradeName("Antirheumatics")
                .withActiveIngredient("amoxicillin")
                .withDirection("Taken by mouth")
                .withPurpose("treat pain")
                .withSideEffect("allergic reaction")
                .withStorageCount("123").build()));
    }
}
