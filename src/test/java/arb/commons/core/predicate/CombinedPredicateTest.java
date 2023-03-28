package arb.commons.core.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.model.client.predicates.NameContainsKeywordsPredicate;

public class CombinedPredicateTest {
    @Test
    public void equals() {
        Predicate<String> firstPredicate = s -> true;
        Predicate<String> secondPredicate = s -> false;

        CombinedPredicate<String> firstCombinedPredicate = new CombinedPredicate<>(Arrays.asList(firstPredicate));
        CombinedPredicate<String> secondCombinedPredicate = new CombinedPredicate<>(Arrays.asList(secondPredicate));
        CombinedPredicate<String> thirdCombinedPredicate =
                new CombinedPredicate<>(Arrays.asList(firstPredicate, secondPredicate));
        CombinedPredicate<String> fourthCombinedPredicate =
                new CombinedPredicate<>(Arrays.asList(secondPredicate, firstPredicate));

        assertTrue(firstCombinedPredicate.equals(firstCombinedPredicate)); // same object
        assertFalse(firstCombinedPredicate.equals(secondCombinedPredicate)); // different predicates
        assertFalse(firstCombinedPredicate.equals(null)); // null
        assertFalse(firstCombinedPredicate.equals(0)); // different type

        assertTrue(thirdCombinedPredicate.equals(fourthCombinedPredicate)); // same predicates, different order

        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Ben"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Ben", "Alice"));
        CombinedPredicate<Client> firstClientCombinedPredicate = new CombinedPredicate<>(Arrays.asList(namePredicate));
        CombinedPredicate<Client> secondClientCombinedPredicate =
                new CombinedPredicate<>(Arrays.asList(secondNamePredicate));
        assertTrue(firstClientCombinedPredicate.equals(secondClientCombinedPredicate));
    }
}
