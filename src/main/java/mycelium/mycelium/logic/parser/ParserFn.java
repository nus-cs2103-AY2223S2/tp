package mycelium.mycelium.logic.parser;

import mycelium.mycelium.logic.parser.exceptions.ParseException;

/**
 * This is essentially a java.util.function.Function which might throw a {@link ParseException}. The type is made
 * explicit here so that it is more convenient to pass methods around as lambdas.
 */
@FunctionalInterface
public interface ParserFn<T, U> {
    U parse(T t) throws ParseException;
}
