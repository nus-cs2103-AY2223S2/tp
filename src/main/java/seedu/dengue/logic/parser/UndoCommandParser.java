package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dengue.logic.commands.UndoCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * A parser for the arguments of the undo command.
 */
public class UndoCommandParser implements Parser<UndoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UndoCommand
     * and returns a UndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new UndoCommand(1);
        }
        try {
            int numberofUndos = Integer.parseInt(trimmedArgs);
            return new UndoCommand(numberofUndos);
        } catch (Exception err) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }
    }
}
