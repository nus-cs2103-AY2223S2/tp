package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and create a new RemoveMeetingCommand Object
 */
public class RemoveMeetingCommandParser implements Parser<RemoveMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveMeetingCommand
     * @param args String of arguments to be parsed
     * @return RemoveMeetingCommand object
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemoveMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            String[] indexes = args.trim().split(" ");
            Index indexPerson = ParserUtil.parseIndex(indexes[0]);
            Index indexMeeting = ParserUtil.parseIndex(indexes[1]);
            return new RemoveMeetingCommand(indexPerson, indexMeeting);
        } catch (ParseException parseErr) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE, parseErr)
            );
        }
    }
}
