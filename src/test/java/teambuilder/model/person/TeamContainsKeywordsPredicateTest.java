package teambuilder.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import teambuilder.testutil.PersonBuilder;

class TeamContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TeamContainsKeywordsPredicate firstPredicate = new TeamContainsKeywordsPredicate(firstPredicateKeywordList);
        TeamContainsKeywordsPredicate secondPredicate = new TeamContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TeamContainsKeywordsPredicate firstPredicateCopy = new TeamContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_teamContainsKeywords_returnsTrue() {
        // One keyword
        TeamContainsKeywordsPredicate predicate = new TeamContainsKeywordsPredicate(Collections.singletonList("TeamA"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTeams("TeamA").build()));

        // Multiple keywords
        predicate = new TeamContainsKeywordsPredicate(Arrays.asList("TeamA", "TeamB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTeams("TeamA TeamB").build()));

        // Only one matching keyword
        predicate = new TeamContainsKeywordsPredicate(Arrays.asList("TeamA", "TeamB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTeams("TeamA").build()));

        // Mixed-case keywords
        predicate = new TeamContainsKeywordsPredicate(Arrays.asList("teaMa", "tEAmB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTeams("TeamA TeamB").build()));

    }

    @Test
    public void test_teamDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TeamContainsKeywordsPredicate predicate = new TeamContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTeams("TeamA").build()));

        // Non-matching keyword
        predicate = new TeamContainsKeywordsPredicate(Arrays.asList("TeamA"));
        assertFalse(predicate.test(new PersonBuilder().withTeams("TeamB").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TeamContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withTeams("TeamA").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}