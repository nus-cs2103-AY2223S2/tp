package seedu.address.logic.parser.documents;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.DeleteDocumentsCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDocumentsCommand object
 */
public class DeleteDocumentsCommandParser implements Parser<DeleteDocumentsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDocumentsCommand
     * and returns a DeleteDocumentsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDocumentsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDocumentsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDocumentsCommand.MESSAGE_USAGE), pe);
        }
    }
}
