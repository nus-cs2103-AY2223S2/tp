package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.application.InternshipStatus.PENDING;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.InternshipBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("JP"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("JP", "Morgan"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Google", "Meta", "Morgan"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("jP", "mOrGAN"));
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Google"));
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("JP Morgan").build()));

        // Keywords match status, email and phone, but do not match company name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("RECEIVED", "google@email.com", "87654321"));
        assertFalse(predicate.test(new InternshipBuilder()
                .withCompanyName("Goo")
                .withStatus(PENDING)
                .withContact(new Contact(new Phone("87654321"), new Email("google@gmail.com"))).build()));
    }
}
