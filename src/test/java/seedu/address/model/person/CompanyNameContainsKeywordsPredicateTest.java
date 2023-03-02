package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.testutil.InternshipBuilder;

public class CompanyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyNameContainsKeywordsPredicate firstPredicateCopy =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Multiple keywords
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Apple", "Google"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Only one matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Google", "Grab"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Grab").build()));

        // Mixed-case keywords
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("aPPle", "gOOGLE"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple").build()));

        // Non-matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Grab"));
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Keywords match role, status and date, but does not match name
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Developer", "applied", "2023-02-01"));
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple").withRole("Developer")
                .withStatus("applied").withDate("2023-02-01").build()));
    }
}
