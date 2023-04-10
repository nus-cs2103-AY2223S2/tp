package seedu.internship.logic.parser;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.CopyCommand;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
            assert index.getZeroBased() > -1;
        } catch (ParseException pe) {
            ParseException e = ParserUtil.handleIndexException(pe, CopyCommand.MESSAGE_USAGE);
            throw e;
        }
        return new CopyCommand(index);
    }

}
