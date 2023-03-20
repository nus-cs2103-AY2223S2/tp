package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
@FunctionalInterface
public interface LogicCommandParser<T extends LogicCommand> {

    /**
     * Gets the internal parser of this instance.
     */
    ApplicativeParser<T> getInternalParser();

    /**
     * Runs this parser.
     */
    default T parse(String userInput) {
        return getInternalParser().parse(userInput);
    }

    default LogicCommandParser<T> or(LogicCommandParser<? extends T> commandParser) {
        ApplicativeParser<T> internal = getInternalParser().or(commandParser.getInternalParser());
        return () -> internal;
    }

    default LogicCommandParser<LogicCommand> safeCast() {
        @SuppressWarnings("unchecked")
        LogicCommandParser<LogicCommand> castedThis = (LogicCommandParser<LogicCommand>) this;
        return castedThis;
    }

    default LogicCommandParser<T> throwIfFail(String errorMessage) {
        ApplicativeParser<T> internal = getInternalParser().throwIfFail(errorMessage);
        return () -> internal;
    }
}
