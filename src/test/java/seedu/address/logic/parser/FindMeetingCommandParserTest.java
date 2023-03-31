package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;


import seedu.address.logic.parser.exceptions.ParseException;




import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.commands.ListRegionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Region;

public class FindMeetingCommandParserTest {
    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    private final String startDateTime = "09-11-2001 11:30";
    private final LocalDateTime date = LocalDateTime.of(2001, 11, 9, 11, 30);

    @Test
    public void parse_returnsCorrectMeeting() throws ParseException {
        assertEquals(parser.parse(startDateTime), new FindMeetingCommand(date));

    }

}