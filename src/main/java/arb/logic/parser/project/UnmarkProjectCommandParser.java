package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import arb.commons.core.index.Index;
import arb.logic.commands.project.UnmarkProjectCommand;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkProjectCommand object
 */
public class UnmarkProjectCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkProjectCommand
     * and returns an UnmarkProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkProjectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkProjectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkProjectCommand.MESSAGE_USAGE), pe);
        }
    }
}

