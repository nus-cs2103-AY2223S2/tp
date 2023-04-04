package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkPresentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and mark attendance of a student as present.
 */
public class MarkCommandParser implements Parser<MarkPresentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkPresentCommand
     * and returns a MarkPresentCommand object.
     * @param args the input arguments to be parsed
     * @return a MarkPresentCommand object based on the input arguments provided
     * @throws ParseException if the input arguments are not in the expected format
     */
    public MarkPresentCommand parse(String args) throws ParseException {
        try {
            Index[] index = ParserUtil.parseMultipleIndex(args);
            return new MarkPresentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    pe.getMessage() + MarkPresentCommand.MESSAGE_USAGE);
        }
    }
}
