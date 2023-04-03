package seedu.address.logic.parser;

import java.time.LocalDateTime;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;

/**
     * Parses input arguments and creates a new ListRegionCommand object
     */
    public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

        @Override
        public FindMeetingCommand parse(String userInput) throws ParseException {
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
