package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.attendance.AttendanceCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class AttendanceCommandParser implements Parser<AttendanceCommand> {
    public AttendanceCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AttendanceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE), pe);
        }    }
}
