package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INDICATE_POSITIVE;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dengue.logic.commands.RedoCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * A parser for the arguments of the redo command.
 */
public class RedoCommandParser extends UndoRedoCommandParser implements Parser<RedoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RedoCommand
     * and returns a RedoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RedoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new RedoCommand(1);
        }
        try {
            int numberofRedos = Integer.parseInt(trimmedArgs);
            requirePositive(numberofRedos);
            return new RedoCommand(numberofRedos);
        } catch (NumberFormatException err) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        }
    }

    @Override
    public void requirePositive(int number) throws ParseException {
        if (number <= 0) {
            throw new ParseException(
                    String.format(MESSAGE_INDICATE_POSITIVE, RedoCommand.MESSAGE_USAGE));
        }
    }
}
