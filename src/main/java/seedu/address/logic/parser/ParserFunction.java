package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Provides an interface for anonymous functions to obey general
 * syntactic rules for parsing of queries.
 *
 * @param <T> Type parameter for the function parameters
 * @param <R> Type parameter for the function return type
 */
@FunctionalInterface
public interface ParserFunction<T, R> {
    R apply(T t) throws ParseException;
}
