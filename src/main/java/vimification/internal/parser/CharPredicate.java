package vimification.internal.parser;

@FunctionalInterface
public interface CharPredicate {

    boolean test(char c);

    default CharPredicate negate() {
        return c -> !test(c);
    }

    default CharPredicate and(CharPredicate other) {
        return c -> test(c) && other.test(c);
    }

    default CharPredicate or(CharPredicate other) {
        return c -> test(c) || other.test(c);
    }
}
