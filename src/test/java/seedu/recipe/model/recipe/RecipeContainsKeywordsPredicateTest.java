package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.testutil.RecipeBuilder;

public class RecipeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecipeContainsKeywordsPredicate firstPredicate =
                new RecipeContainsKeywordsPredicate(firstPredicateKeywordList);
        RecipeContainsKeywordsPredicate secondPredicate =
                new RecipeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecipeContainsKeywordsPredicate firstPredicateCopy =
                new RecipeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different recipe -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        RecipeContainsKeywordsPredicate predicate =
                new RecipeContainsKeywordsPredicate(Collections.singletonList("Corndog"));
        assertTrue(predicate.test(new RecipeBuilder().withTitle("Corndog with cheese").build()));

        // Multiple keywords
        predicate = new RecipeContainsKeywordsPredicate(Arrays.asList("Corndog", "cheese"));
        assertTrue(predicate.test(new RecipeBuilder().withTitle("Corndog with cheese").build()));

        // Only one matching keyword
        predicate = new RecipeContainsKeywordsPredicate(Arrays.asList("Corndog", "cheese"));
        assertTrue(predicate.test(new RecipeBuilder().withTitle("Corndog with tomato").build()));

        // Mixed-case keywords
        predicate = new RecipeContainsKeywordsPredicate(Arrays.asList("coRnDog", "chEese"));
        assertTrue(predicate.test(new RecipeBuilder().withTitle("Corndog with cheese").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RecipeContainsKeywordsPredicate predicate = new RecipeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RecipeBuilder().withTitle("Corndog").build()));

        // Non-matching keyword
        predicate = new RecipeContainsKeywordsPredicate(Arrays.asList("Tomato"));
        assertFalse(predicate.test(new RecipeBuilder().withTitle("Corndog with cheese").build()));
    }
}
