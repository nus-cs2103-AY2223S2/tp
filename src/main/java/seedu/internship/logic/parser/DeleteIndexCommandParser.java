package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import java.util.List;
import java.util.stream.Collectors;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.DeleteIndexCommand;
import seedu.internship.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteIndexCommandParser implements Parser<DeleteIndexCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteIndexCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS, DeleteIndexCommand.MESSAGE_USAGE));
        }
        try {
            List<Index> indexes = ParserUtil.parseIndexes(args);
            assert indexes != null;
            return new DeleteIndexCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIndexCommand.MESSAGE_USAGE), pe);
        }
    }
}
