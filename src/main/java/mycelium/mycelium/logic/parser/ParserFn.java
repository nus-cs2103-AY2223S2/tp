package mycelium.mycelium.logic.parser;

import mycelium.mycelium.logic.parser.exceptions.ParseException;

/**
 * This is essentially a java.util.function.Function which might throw a {@link
 * ParseException}. The type is made explicit here so that it is more convenient
 * to pass methods around as * lambdas. It defers from {@link Parser} in that it
 * returns any type, not just a command. Thus, its main use is in parsing
 * arguments within each command string.
 */
@FunctionalInterface
public interface ParserFn<T, U> {
    U parse(T t) throws ParseException;
}
