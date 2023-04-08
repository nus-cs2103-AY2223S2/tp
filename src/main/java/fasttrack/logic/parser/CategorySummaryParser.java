package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.general.CategorySummaryCommand;
import fasttrack.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CategorySummaryCommand object
 */
public class CategorySummaryParser implements Parser<CategorySummaryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CategorySummaryCommand
     * and returns a CategorySummaryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CategorySummaryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CategorySummaryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CategorySummaryCommand.MESSAGE_USAGE), pe);
        }
    }

}
