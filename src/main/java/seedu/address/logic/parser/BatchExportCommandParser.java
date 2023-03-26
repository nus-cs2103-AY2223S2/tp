package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BatchExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new BatchExportCommand object
 */
public class BatchExportCommandParser implements Parser<BatchExportCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BatchExportCommand
     * and returns an BatchExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchExportCommand parse(String args) throws ParseException {
        try {
            String fileName = ParserUtil.parseExport(args);
            return new BatchExportCommand(fileName);
        } catch (ParseException exception) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchExportCommand.MESSAGE_USAGE), exception);
        }
    }

}
