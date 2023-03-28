package arb.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.testutil.ClientBuilder;

public class ClientContainsTagPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagList = Collections.singletonList("first");
        List<String> secondPredicateTagList = Arrays.asList("first", "second");

        ClientContainsTagPredicate firstPredicate = new ClientContainsTagPredicate(firstPredicateTagList);
        ClientContainsTagPredicate secondPredicate = new ClientContainsTagPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientContainsTagPredicate firstPredicateCopy = new ClientContainsTagPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        ClientContainsTagPredicate secondPredicateCopy =
                new ClientContainsTagPredicate(Arrays.asList("second", "first"));
        assertTrue(secondPredicateCopy.equals(secondPredicate)); // different order -> returns true
    }

    @Test
    public void test_clientContainsTag_returnsTrue() {
        // One tag
        ClientContainsTagPredicate predicate = new ClientContainsTagPredicate(Collections.singletonList("friend"));
        assertTrue(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Multiple tags
        predicate = new ClientContainsTagPredicate(Arrays.asList("friend", "husband"));
        assertTrue(predicate.test(new ClientBuilder().withTags("frined", "husband").build()));

        // Only one matching tag
        predicate = new ClientContainsTagPredicate(Arrays.asList("friend", "husband"));
        assertTrue(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Mixed-case tags
        predicate = new ClientContainsTagPredicate(Arrays.asList("frIenD", "huSBand"));
        assertTrue(predicate.test(new ClientBuilder().withTags("friend", "husband").build()));
    }

    @Test
    public void test_clientDoesNotContainTags_returnsFalse() {
        // Zero tags
        ClientContainsTagPredicate predicate = new ClientContainsTagPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Non-matching tag
        predicate = new ClientContainsTagPredicate(Arrays.asList("husband"));
        assertFalse(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Tags match name but does not match tag
        predicate = new ClientContainsTagPredicate(Arrays.asList("Alice"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withTags("friend").build()));
    }
}
