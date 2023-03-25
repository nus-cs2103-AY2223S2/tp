package seedu.address.logic.parser;

import seedu.address.logic.commands.BatchAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class BatchAddCommandParser implements Parser<BatchAddCommand> {

    public BatchAddCommand parse(String args) throws ParseException {
        try
        {
            String fileName = ParserUtil.parseFileName(args);
            return new BatchAddCommand(fileName);
        } catch (ParseException exception)
        {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE), exception);
        }
    }

}
