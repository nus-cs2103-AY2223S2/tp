package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.attendance.MarkPresentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and mark attendance of a student
 */
public class MarkCommandParser implements Parser<MarkPresentCommand> {
    public MarkPresentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkPresentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPresentCommand.MESSAGE_USAGE), pe);
        }
    }
}
