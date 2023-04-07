package vimification.internal.parser;

/**
 * Represents a predicate that accepts a single {@code char} as an argument.
 */
@FunctionalInterface
public interface CharPredicate {

    /**
     * Tests the given character.
     *
     * @param c the given character
     * @return true if the character passes the test, otherwise false
     */
    boolean test(char c);

    /**
     * Negates this predicate.
     *
     * @return the negation of this predicate
     */
    default CharPredicate negate() {
        return c -> !test(c);
    }

    /**
     * Combines this predicate with the other predicate using AND operator.
     *
     * @param other the other predicate
     * @return the combined predicate
     */
    default CharPredicate and(CharPredicate other) {
        return c -> test(c) && other.test(c);
    }

    /**
     * Combines this predicate with the other predicate using OR operator.
     *
     * @param other the other predicate
     * @return the combined predicate
     */
    default CharPredicate or(CharPredicate other) {
        return c -> test(c) || other.test(c);
    }
}
