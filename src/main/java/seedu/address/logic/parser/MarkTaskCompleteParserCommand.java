package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkTaskCompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskCompleteParserCommand object
 */
public class MarkTaskCompleteParserCommand implements Parser<MarkTaskCompleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCompleteCommand
     * and returns an MarkTaskCompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCompleteCommand parse(String args) throws ParseException {
        try {
            Index studentIndex = ParserUtil.parseFirstIndex(args);
            Index taskIndex = ParserUtil.parseSecondIndex(args);
            return new MarkTaskCompleteCommand(studentIndex, taskIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCompleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
