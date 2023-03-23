package seedu.address.model.util;

import java.util.function.Predicate;

/**
 * Composes any number of predicates together.
 * @param <T> the input type for predicates
 */
public class ComposedPredicate<T> implements Predicate<T> {

    private Predicate<? super T> composedPredicate;


    /**
     * Creates a Predicate that is composed of the base and predicate vararg given.
     * @param base the current predicate
     * @param predicates predicate to compose with the base
     */
    public ComposedPredicate(Predicate<? super T> base, Predicate<T>... predicates) {
        if (base != null) {
            composedPredicate = base;
        } else {
            composedPredicate = input -> true;
        }
        for (Predicate<T> predicate : predicates) {
            composedPredicate = predicate.and(composedPredicate);
        }
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     *     otherwise {@code false}
     */
    @Override
    public boolean test(T t) {
        return composedPredicate.test(t);
    }


}
