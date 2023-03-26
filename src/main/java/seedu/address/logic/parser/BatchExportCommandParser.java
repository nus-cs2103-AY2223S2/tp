package seedu.address.logic.parser;

import seedu.address.logic.commands.BatchExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class BatchExportCommandParser implements Parser<BatchExportCommand> {

    public BatchExportCommand parse(String args) throws ParseException {
        try
        {
            String fileName = ParserUtil.parseExport(args);
            return new BatchExportCommand(fileName);
        } catch (ParseException exception)
        {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchExportCommand.MESSAGE_USAGE), exception);
        }
    }

}
