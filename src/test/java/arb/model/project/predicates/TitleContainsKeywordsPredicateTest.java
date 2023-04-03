package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate = PredicateUtil.getTitleContainsKeywordsPredicate("first");
        TitleContainsKeywordsPredicate secondPredicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("first", "second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                PredicateUtil.getTitleContainsKeywordsPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        TitleContainsKeywordsPredicate secondPredicateCopy =
                PredicateUtil.getTitleContainsKeywordsPredicate("second", "first");
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate = PredicateUtil.getTitleContainsKeywordsPredicate("Sky");
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Multiple keywords
        predicate = PredicateUtil.getTitleContainsKeywordsPredicate("Sky", "Painting");
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Only one matching keyword
        predicate = PredicateUtil.getTitleContainsKeywordsPredicate("Sky", "Oil");
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Mixed-case keywords
        predicate = PredicateUtil.getTitleContainsKeywordsPredicate("sKy", "PaiNTing");
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = PredicateUtil.getTitleContainsKeywordsPredicate();
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Non-matching keyword
        predicate = PredicateUtil.getTitleContainsKeywordsPredicate("Oil");
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Keywords match deadline but does not match title
        predicate = PredicateUtil.getTitleContainsKeywordsPredicate("2000-01-01");
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").withDeadline("2000-01-01")
                .build()));
    }
}
