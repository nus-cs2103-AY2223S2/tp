package seedu.address.model.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(
                firstPredicateKeywordList, CliSyntax.PREFIX_NAME);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(
                secondPredicateKeywordList, CliSyntax.PREFIX_NAME);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(
                firstPredicateKeywordList, CliSyntax.PREFIX_NAME);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(
                Collections.singletonList("Albert"), CliSyntax.PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Albert Bart").build()));

        // Multiple keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Albert", "Bart"), CliSyntax.PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Albert Bart").build()));

        // Only one matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Bart", "Carol"), CliSyntax.PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Albert Carol").build()));

        // Mixed-case keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("aLBert", "bARt"), CliSyntax.PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Albert Bart").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(
                Collections.emptyList(), CliSyntax.PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Albert").build()));

        // Non-matching keyword
        predicate = new ContainsKeywordsPredicate(List.of("Carol"), CliSyntax.PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Albert Bart").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"), CliSyntax.PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Albert").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
