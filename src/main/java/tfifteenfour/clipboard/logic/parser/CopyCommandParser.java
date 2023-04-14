package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.CopyCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CopyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE), pe);
        }
    }

}
