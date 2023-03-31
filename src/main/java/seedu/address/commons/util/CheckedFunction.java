package seedu.address.commons.util;

import seedu.address.logic.commands.exceptions.RecommendationException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws RecommendationException;
}