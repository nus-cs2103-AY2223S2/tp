package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;


import seedu.address.logic.commands.FindMeetingCommand;

import java.time.LocalDateTime;

/**
     * Parses input arguments and creates a new ListRegionCommand object
     */
    public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

        @Override
        public FindMeetingCommand parse(String userInput) throws ParseException {
            LocalDateTime meetingStart = ParserUtil.parseDateTime(userInput);
            return new FindMeetingCommand(meetingStart);
        }

    }
