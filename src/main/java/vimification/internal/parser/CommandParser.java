package vimification.internal.parser;

import java.util.function.Function;

import vimification.internal.command.Command;

/**
 * Represents a parser that is able to parse user input into a {@code LogicCommand} of type
 * {@code T}.
 * <p>
 * Note that, we need two layers of {@code ApplicativeParser}. The first layer is to parse the
 * prefix, and the second layer is to parse the actual {@code LogicCommand}.
 */
@FunctionalInterface
public interface CommandParser<T extends Command> {

    public static <T extends Command> CommandParser<T> fail() {
        return ApplicativeParser::fail;
    }

    ApplicativeParser<ApplicativeParser<T>> getInternalParser();

    default T parse(String input) {
        return getInternalParser().flatMap(Function.identity()).parse(input);
    }

    default CommandParser<T> or(CommandParser<? extends T> that) {
        ApplicativeParser<ApplicativeParser<T>> thisInternal = getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> thatInternal = that.<T>cast().getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> combinedInternal = thisInternal.or(thatInternal);
        return () -> combinedInternal;
    }

    @SuppressWarnings("unchecked")
    default <U extends Command> CommandParser<U> cast() {
        return (CommandParser<U>) this;
    }

    default CommandParser<T> updateInternalParser(
            Function<ApplicativeParser<ApplicativeParser<T>>, ApplicativeParser<ApplicativeParser<T>>> mapper) {
        ApplicativeParser<ApplicativeParser<T>> newInternal = mapper.apply(getInternalParser());
        return () -> newInternal;
    }
}
