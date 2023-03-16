package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InternshipContainsKeywordsPredicate firstPredicate =
                new InternshipContainsKeywordsPredicate(firstPredicateKeywordList, firstPredicateKeywordList,
                        firstPredicateKeywordList, firstPredicateKeywordList);
        InternshipContainsKeywordsPredicate secondPredicate =
                new InternshipContainsKeywordsPredicate(secondPredicateKeywordList, secondPredicateKeywordList,
                        secondPredicateKeywordList, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InternshipContainsKeywordsPredicate firstPredicateCopy =
                new InternshipContainsKeywordsPredicate(firstPredicateKeywordList, firstPredicateKeywordList,
                        firstPredicateKeywordList, firstPredicateKeywordList);
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
        // One name keyword
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate(Collections.singletonList("Apple"),
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Multiple name keywords
        predicate = new InternshipContainsKeywordsPredicate(Arrays.asList("Apple", "Google"),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Only one matching name keyword
        predicate = new InternshipContainsKeywordsPredicate(Arrays.asList("Google", "Grab"),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Grab").build()));

        // Mixed-case name keywords
        predicate = new InternshipContainsKeywordsPredicate(Arrays.asList("aPPle", "gOOGLE"),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        // One role keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(),
                Collections.singletonList("developer"), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withRole("developer").build()));

        // Multiple role keywords
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(),
                Arrays.asList("software", "developer"), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withRole("software developer").build()));

        // Only one matching role keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(),
                Arrays.asList("developer", "engineer"), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withRole("software developer").build()));

        // Mixed-case status keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(),
                Arrays.asList("soFTwaRE", "dEVELOPer"), Collections.emptyList(), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withRole("software developer").build()));

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        // One status keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.singletonList("applied"), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withStatus("applied").build()));

        // Only one matching status keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("new", "applied"), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withStatus("applied").build()));

        // Mixed-case status keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("oFFered", "reJECted"), Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withStatus("offered").build()));

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        // One tag keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Collections.singletonList("python"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("python", "java").build()));

        // Multiple tag keywords
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Arrays.asList("python", "java"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("python", "java").build()));

        // Only one matching tag keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Arrays.asList("python", "java"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("python", "rust").build()));

        // Mixed-case tag keywords
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Arrays.asList("pYTHon", "jAVA"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("python", "java").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple").build()));

        // Non-matching name keyword
        predicate = new InternshipContainsKeywordsPredicate(Arrays.asList("Grab"), Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple Google").build()));

        // Non-matching status keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("new"), Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withStatus("rejected").build()));

        // Non-matching tag keyword
        predicate = new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Arrays.asList("python"));
        assertFalse(predicate.test(new InternshipBuilder().withTags("java").build()));

        // Keywords match role, status and date, but does not match name
        predicate = new InternshipContainsKeywordsPredicate(Arrays.asList("Developer", "applied", "2023-02-01"),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Apple").withRole("Developer")
                .withStatus("applied").withDate("2023-02-01").build()));
    }
}