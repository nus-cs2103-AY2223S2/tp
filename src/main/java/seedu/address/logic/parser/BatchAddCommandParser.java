package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BatchAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new BatchAddCommand object
 */
public class BatchAddCommandParser implements Parser<BatchAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BatchAddCommand
     * and returns an BatchAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchAddCommand parse(String args) throws ParseException {
        try {
            String fileName = ParserUtil.parseFileName(args);
            return new BatchAddCommand(fileName);
        } catch (ParseException exception) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE), exception);
        }
    }

}
