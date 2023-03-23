package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.GoToCommand;
import seedu.library.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new GoToCommand object
 */
public class GoToCommandParser implements Parser<GoToCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GoToCommand
     * and returns a GoToCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoToCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GoToCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE), pe);
        }
    }

}

