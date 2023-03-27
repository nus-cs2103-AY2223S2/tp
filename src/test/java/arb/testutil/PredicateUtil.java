package arb.testutil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import arb.commons.core.predicate.CombinedPredicate;
import arb.model.client.predicates.ClientContainsTagPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;

/**
 * A utility class for predicates.
 */
public class PredicateUtil {

    /**
     * Returns a {@code CombinedPredicate} using the given predicates.
     */
    @SuppressWarnings("unchecked")
    public static <T> CombinedPredicate<T> getCombinedPredicate(Predicate<T> ... predicates) {
        return new CombinedPredicate<>(Arrays.asList(predicates));
    }

    /**
     * Returns a {@code ClientContainsTagPredicate} with the given {@code tags}.
     */
    public static ClientContainsTagPredicate getClientContainsTagPredicate(List<String> tags) {
        return new ClientContainsTagPredicate(tags);
    }

    /*public static ProjectContainsTagPredicate getProjectContainsTagPredicate(List<String> tags) {
        return new ProjectContainsTagPredicate(tags);
    }*/

    /**
     * Returns a {@code NameContainsKeywordsPredicate} with the given {@code names}.
     */
    public static NameContainsKeywordsPredicate getNameContainsKeywordsPredicate(List<String> names) {
        return new NameContainsKeywordsPredicate(names);
    }

    /**
     * Returns a {@code TitleContainsKeywordsPredicate} with the given {@code names}.
     */
    public static TitleContainsKeywordsPredicate getTitleContainsKeywordsPredicate(List<String> names) {
        return new TitleContainsKeywordsPredicate(names);
    }
}
