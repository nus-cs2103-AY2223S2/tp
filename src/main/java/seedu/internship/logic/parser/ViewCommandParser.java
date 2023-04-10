package seedu.internship.logic.parser;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.ViewCommand;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            ParseException e = ParserUtil.handleIndexException(pe, ViewCommand.MESSAGE_USAGE);
            throw e;
        }

        return new ViewCommand(index);
    }

}
