package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.attendancecommand.SessionCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SessionCommand object.
 */
public class SessionCommandParser implements Parser<SessionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SessionCommand
     * and returns an SessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SessionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SessionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SessionCommand.MESSAGE_USAGE), pe);
        }
    }
}
