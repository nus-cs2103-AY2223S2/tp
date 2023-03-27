package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkTaskInProgressCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskInProgressParserCommand object
 */
public class MarkTaskInProgressParserCommand implements Parser<MarkTaskInProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskInProgressCommand
     * and returns an MarkTaskInProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskInProgressCommand parse(String args) throws ParseException {
        try {
            Index studentIndex = ParserUtil.parseFirstIndex(args);
            Index taskIndex = ParserUtil.parseSecondIndex(args);
            return new MarkTaskInProgressCommand(studentIndex, taskIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskInProgressCommand.MESSAGE_USAGE), pe);
        }
    }
}
