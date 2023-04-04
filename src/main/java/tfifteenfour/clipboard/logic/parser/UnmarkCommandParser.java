package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkAbsentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and mark attendance of a student as absent.
 */
public class UnmarkCommandParser implements Parser<MarkAbsentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkAbsentCommand
     * and returns a MarkAbsentCommand object.
     * @param args the input arguments to be parsed
     * @return a MarkAbsentCommand object based on the input arguments provided
     * @throws ParseException if the input arguments are not in the expected format
     */
    public MarkAbsentCommand parse(String args) throws ParseException {
        try {
            Index[] index = ParserUtil.parseMultipleIndex(args);
            return new MarkAbsentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    pe.getMessage() + MarkAbsentCommand.MESSAGE_USAGE);
        }
    }
}
