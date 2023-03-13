package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import arb.commons.core.index.Index;
import arb.logic.commands.project.MarkProjectCommand;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MarkProjectCommand object
 */
public class MarkProjectCommandParser implements Parser<MarkProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkProjectCommand
     * and returns an MarkProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkProjectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkProjectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkProjectCommand.MESSAGE_USAGE), pe);
        }
    }
}
