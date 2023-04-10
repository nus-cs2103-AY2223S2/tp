package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import java.util.List;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.DeleteIndexCommand;
import seedu.internship.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteIndexCommandParser implements Parser<DeleteIndexCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteIndexCommand
     * and returns a DeleteIndexCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteIndexCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS, DeleteIndexCommand.MESSAGE_USAGE));
        }

        List<Index> indexes;

        try {
            indexes = ParserUtil.parseIndexes(args);
            assert indexes != null;
        } catch (ParseException pe) {
            ParseException e = ParserUtil.handleIndexException(pe, DeleteIndexCommand.MESSAGE_USAGE);
            throw e;
        }

        return new DeleteIndexCommand(indexes);
    }
}
