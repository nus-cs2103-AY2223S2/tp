package arb.commons.core.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.testutil.PredicateUtil;

public class CombinedPredicateTest {

    @Test
    @SuppressWarnings("unchecked")
    public void equals() {
        Predicate<String> firstPredicate = s -> true;
        Predicate<String> secondPredicate = s -> false;

        CombinedPredicate<String> firstCombinedPredicate = PredicateUtil.getCombinedPredicate(firstPredicate);
        CombinedPredicate<String> secondCombinedPredicate = PredicateUtil.getCombinedPredicate(secondPredicate);
        CombinedPredicate<String> thirdCombinedPredicate =
                PredicateUtil.getCombinedPredicate(firstPredicate, secondPredicate);
        CombinedPredicate<String> fourthCombinedPredicate =
                PredicateUtil.getCombinedPredicate(secondPredicate, firstPredicate);

        assertTrue(firstCombinedPredicate.equals(firstCombinedPredicate)); // same object
        assertFalse(firstCombinedPredicate.equals(secondCombinedPredicate)); // different predicates
        assertFalse(firstCombinedPredicate.equals(null)); // null
        assertFalse(firstCombinedPredicate.equals(0)); // different type

        assertTrue(thirdCombinedPredicate.equals(fourthCombinedPredicate)); // same predicates, different order

        // two name predicates with the same keywords but in different order
        NameContainsKeywordsPredicate namePredicate = PredicateUtil.getNameContainsKeywordsPredicate("Alice", "Ben");
        NameContainsKeywordsPredicate secondNamePredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("Ben", "Alice");
        CombinedPredicate<Client> firstClientCombinedPredicate = PredicateUtil.getCombinedPredicate(namePredicate);
        CombinedPredicate<Client> secondClientCombinedPredicate =
                PredicateUtil.getCombinedPredicate(secondNamePredicate);
        assertTrue(firstClientCombinedPredicate.equals(secondClientCombinedPredicate));
    }
}
