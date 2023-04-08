package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindMeetingCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, FindMeetingCommand.MESSAGE_USAGE)
            );
        } else if (userInput.contains("-")) {
            LocalDate meetingStart = ParserUtil.parseDate(userInput);
            return new FindMeetingCommand(meetingStart);
        }
        Index person = ParserUtil.parseIndex(userInput);
        return new FindMeetingCommand(person);
    }

    /*
    @Override
    public FindMeetingCommand parse(String userInput) throws ParseException {
        LocalDateTime meetingStart = ParserUtil.parseDateTime(userInput);
        return new FindMeetingCommand(meetingStart);
    }*/

}
