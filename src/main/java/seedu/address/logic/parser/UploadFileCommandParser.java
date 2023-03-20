package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of the UploadFileCommand
 * and returns a UploadFileCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class UploadFileCommandParser implements Parser<UploadFileCommand> {

    @Override
    public UploadFileCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UploadFileCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadFileCommand.MESSAGE_USAGE), pe);
        }
    }
}
