package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class LanguageContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandLanguagePredicate firstPredicate = new FindCommandLanguagePredicate(
                firstPredicateKeywordList);
        FindCommandLanguagePredicate secondPredicate = new FindCommandLanguagePredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandLanguagePredicate firstPredicateCopy = new FindCommandLanguagePredicate(
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
    public void test_languageContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandLanguagePredicate predicate = new FindCommandLanguagePredicate(
                Collections.singletonList("java"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withLanguages("java", "python")
                .build()));

        // Multiple keywords
        predicate = new FindCommandLanguagePredicate(Arrays.asList("java", "python"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withLanguages("java", "python")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandLanguagePredicate(Arrays.asList("java", "Carol"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .withLanguages("java", "python")
                .build()));

        // Mixed-case keywords
        predicate = new FindCommandLanguagePredicate(Arrays.asList("JaVa", "pyTHon"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withLanguages("java", "python")
                .build()));
    }

    @Test
    public void test_languageDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandLanguagePredicate predicate = new FindCommandLanguagePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withLanguages("java", "python")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandLanguagePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withLanguages("java", "python")
                .build()));

        // Keywords match name, phone, email and address, but does not match language
        predicate = new FindCommandLanguagePredicate(Arrays.asList(
                "alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .withLanguages("java", "python")
                .build()));
    }
}
