package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and create a new RemoveMeetingCommand Object
 */
public class RemoveMeetingCommandParser implements Parser<RemoveMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveMeetingCommand
     *
     * @param args String of arguments to be parsed
     * @return RemoveMeetingCommand object
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemoveMeetingCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_ARGUMENTS, RemoveMeetingCommand.MESSAGE_USAGE));
        }

        String[] indexes = args.trim().split(" ");
        if (indexes.length > 2) {
            String invalidArgumentCountError = "meetingRemove only accepts 2 arguments!";
            throw new ParseException(invalidArgumentCountError);
        }

        try {
            Index indexPerson = ParserUtil.parseIndex(indexes[0]);
            Index indexMeeting = ParserUtil.parseIndex(indexes[1]);
            return new RemoveMeetingCommand(indexPerson, indexMeeting);
        } catch (ParseException parseErr) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE, parseErr)
            );
        } catch (ArrayIndexOutOfBoundsException arrayErr) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_INDEX, RemoveMeetingCommand.MESSAGE_USAGE)
            );
        }
    }
}
