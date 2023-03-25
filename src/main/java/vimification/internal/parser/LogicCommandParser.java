package vimification.internal.parser;

import java.util.function.Function;

import vimification.internal.commands.logic.LogicCommand;

/**
 * Represents a parser that is able to parse user input into a {@code LogicCommand} of type
 * {@code T}.
 * <p>
 * Note that, we need two layers of {@code ApplicativeParser}. The first layer is to parse the
 * prefix, and the second layer is to parse the actual {@code LogicCommand}.
 */
@FunctionalInterface
public interface LogicCommandParser<T extends LogicCommand> {

    ApplicativeParser<ApplicativeParser<T>> getInternalParser();

    default T parse(String input) {
        return getInternalParser().flatMap(Function.identity()).parse(input);
    }

    default LogicCommandParser<T> or(LogicCommandParser<? extends T> that) {
        ApplicativeParser<ApplicativeParser<T>> thisInternal = getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> thatInternal = that.<T>cast().getInternalParser();
        ApplicativeParser<ApplicativeParser<T>> combinedInternal = thisInternal.or(thatInternal);
        return () -> combinedInternal;
    }

    @SuppressWarnings("unchecked")
    default <U extends LogicCommand> LogicCommandParser<U> cast() {
        return (LogicCommandParser<U>) this;
    }

    default LogicCommandParser<T> updateInternalParser(
            Function<ApplicativeParser<ApplicativeParser<T>>, ApplicativeParser<ApplicativeParser<T>>> mapper) {
        ApplicativeParser<ApplicativeParser<T>> newInternal = mapper.apply(getInternalParser());
        return () -> newInternal;
    }

    public static <T extends LogicCommand> LogicCommandParser<T> fail() {
        return ApplicativeParser::fail;
    }

}
