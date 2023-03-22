package arb.commons.core.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** A predicate that tests an Object of type T against all given predicates. */
public class CombinedPredicate<T> implements Predicate<T> {
    private final Set<Predicate<T>> predicates;

    public CombinedPredicate(List<Predicate<T>> predicates) {
        this.predicates = predicates.stream().collect(Collectors.toSet());
    }

    @Override
    public boolean test(T toTest) {
        return predicates.stream().allMatch(pre -> pre.test(toTest));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CombinedPredicate // instanceof handles nulls
                && predicates.equals(((CombinedPredicate<?>) other).predicates)); // state check
    }
}
