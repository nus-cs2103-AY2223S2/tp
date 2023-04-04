package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import java.time.LocalDate;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListRegionCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    @Override
    public FindMeetingCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_ARGUMENTS, FindMeetingCommand.MESSAGE_USAGE)
            );
        }

        LocalDate meetingStart = ParserUtil.parseDate(userInput);
        return new FindMeetingCommand(meetingStart);
    }

    /*
    @Override
    public FindMeetingCommand parse(String userInput) throws ParseException {
        LocalDateTime meetingStart = ParserUtil.parseDateTime(userInput);
        return new FindMeetingCommand(meetingStart);
    }*/

}
