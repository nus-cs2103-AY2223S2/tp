package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface LogicCommandParser<T extends LogicCommand> {

    /**
     * Gets the internal parser of this instance.
     */
    ApplicativeParser<T> getParser();
}
