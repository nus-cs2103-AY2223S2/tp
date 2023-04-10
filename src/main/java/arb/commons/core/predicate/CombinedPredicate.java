package arb.commons.core.predicate;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** A predicate that tests an Object of type T against all given predicates. */
public class CombinedPredicate<T> implements Predicate<T> {
    private final Set<Predicate<T>> predicates;

    /**
     * Constructs a {@code CombinedPredicate} using the given list {@code predicates}.
     * Asserts that there is at least one predicate in {@code predicates}.
     */
    public CombinedPredicate(List<Predicate<T>> predicates) {
        assert !predicates.isEmpty() : "There should be at least one predicate";
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Parameters used were:\n");
        Iterator<Predicate<T>> iterator = predicates.iterator();
        iterator.forEachRemaining(p -> sb.append(p.toString() + "\n"));
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
}
