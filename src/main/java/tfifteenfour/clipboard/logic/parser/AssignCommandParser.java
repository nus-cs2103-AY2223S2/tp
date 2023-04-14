package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.taskcommand.AssignCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignCommand object.
 */
public class AssignCommandParser implements Parser<AssignCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskCommand
     * and returns an TaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        try {
            String[] tokens = ArgumentTokenizer.tokenizeString(args);
            if (tokens.length != 3) {
                throw new ParseException("Invalid number of arguments");
            }

            args = args.trim();

            Index index = ParserUtil.parseIndex(args.split(" ", 2)[0]);
            int grade = Integer.parseInt(args.split(" ", 2)[1]);

            if (grade < 0 || grade > 100) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
            }

            return new AssignCommand(index, grade);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), pe);
        }
    }
}
