package arb.testutil;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import arb.commons.core.predicate.CombinedPredicate;
import arb.model.client.predicates.ClientContainsTagsPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Deadline;
import arb.model.project.Status;
import arb.model.project.predicates.IsOfStatusPredicate;
import arb.model.project.predicates.LinkedClientNameContainsKeywordsPredicate;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.ProjectWithinTimeframePredicate;
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
    public static ClientContainsTagsPredicate getClientContainsTagPredicate(String ... tags) {
        return new ClientContainsTagsPredicate(Arrays.asList(tags));
    }

    /**
     * Returns a {@code ProjectContainsTagPredicate} with the given {@code tags}.
     */
    public static ProjectContainsTagsPredicate getProjectContainsTagsPredicate(String ... tags) {
        return new ProjectContainsTagsPredicate(Arrays.asList(tags));
    }

    /**
     * Returns a {@code NameContainsKeywordsPredicate} with the given {@code names}.
     */
    public static NameContainsKeywordsPredicate getNameContainsKeywordsPredicate(String ... names) {
        return new NameContainsKeywordsPredicate(Arrays.asList(names));
    }

    /**
     * Returns a {@code TitleContainsKeywordsPredicate} with the given {@code names}.
     */
    public static TitleContainsKeywordsPredicate getTitleContainsKeywordsPredicate(String ... names) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(names));
    }

    /**
     * Returns a {@code LinkedClientNameContainsKeywords} with the given {@code names}.
     */
    public static LinkedClientNameContainsKeywordsPredicate getLinkedClientNameContainsKeywordsPredicate(
        String ... names) {
        return new LinkedClientNameContainsKeywordsPredicate(Arrays.asList(names));
    }

    /**
     * Returns a {@code IsOfStatusPredicate} with the given {@code status}.
     */
    public static IsOfStatusPredicate getIsOfStatusPredicate(boolean status) {
        return new IsOfStatusPredicate(new Status(status));
    }

    public static ProjectWithinTimeframePredicate getProjectWithinTimeframePredicate(Optional<String> start,
            Optional<String> end) {
        return new ProjectWithinTimeframePredicate(start.map(d -> new Deadline(d)).orElse(null),
                end.map(d -> new Deadline(d)).orElse(null));
    }
}
