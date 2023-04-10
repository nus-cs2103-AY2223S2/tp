package seedu.address.commons.util;

import seedu.address.logic.commands.exceptions.RecommendationException;

/**
 * Represents a function that accepts an argument of type T and returns a result of type R,
 * which may throw a RecommendationException.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws RecommendationException;
}
