package vimification.internal.parser;

import java.util.function.Function;

import vimification.internal.command.Command;

/**
 * Represents a parser that is able to parse user input into a {@link Command} of type {@code T}.
 * <p>
 * Note that, we need two layers of {@code ApplicativeParser}. The first layer is to parse the
 * prefix, and the second layer is to parse the actual {@code LogicCommand}.
 */
@FunctionalInterface
public interface CommandParser<T extends Command> {

    /**
     * Returns a parser that always fails.
     *
     * @param <T> the type of the command to be parsed
     * @return a parser that always fails
     */
    public static <T extends Command> CommandParser<T> fail() {
        return ApplicativeParser::fail;
    }

    /**
     * Returns the internal parser to be used for parsing user input.
     *
     * @return the internal parser to be used for parsing user input
     */
    ApplicativeParser<ApplicativeParser<T>> getInternalParser();

    /**
     * Parses the user input.
     *
     * @param input the user input
     * @return a command, represented by the user input
     */
    default T parse(String input) {
        return getInternalParser().flatMap(Function.identity()).parse(input);
    }

    /**
     * Creates a new parser that tries this parser first, and then tries the other parser if this
     * parser fails.
     *
     * @param that the other parser
     * @return a new parser that sequentially tries the parsers
     */
    default CommandParser<T> or(CommandParser<? extends T> that) {
        ApplicativeParser<ApplicativeParser<T>> thisInternal = getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> thatInternal = that.<T>cast().getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> combinedInternal = thisInternal.or(thatInternal);
        return () -> combinedInternal;
    }

    /**
     * Casts this parser into the desired type. This is an unsafe operation.
     *
     * @param <U> the type parameter to be casted into
     * @return this parser, but with new type
     */
    @SuppressWarnings("unchecked")
    default <U extends Command> CommandParser<U> cast() {
        return (CommandParser<U>) this;
    }

    /**
     * Updates the internal parser used by this parser.
     *
     * @param mapper the function to update the internal parser
     * @return a new parser with the updated internal parser
     */
    default CommandParser<T> updateInternalParser(
            Function<ApplicativeParser<ApplicativeParser<T>>, ApplicativeParser<ApplicativeParser<T>>> mapper) {
        ApplicativeParser<ApplicativeParser<T>> newInternal = mapper.apply(getInternalParser());
        return () -> newInternal;
    }
}
