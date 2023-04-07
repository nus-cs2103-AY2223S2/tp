package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.AssignCommand;
import seedu.socket.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AssignCommand
     * and returns an AssignCommand object for execution.
     * @param args The user input to be parsed.
     * @return AssignCommand The AssignCommand object to be executed.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // Check if args is not empty.
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        // Check if number of args is correct.
        String[] indexArgs = trimmedArgs.split("\\s+");
        if (indexArgs.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        // Parse args into Index class and return command object.
        Index personIndex;
        Index projectIndex;
        try {
            personIndex = ParserUtil.parseIndex(indexArgs[0]);
            projectIndex = ParserUtil.parseIndex(indexArgs[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignCommand.MESSAGE_USAGE), pe);
        }

        return new AssignCommand(personIndex, projectIndex);
    }
}
