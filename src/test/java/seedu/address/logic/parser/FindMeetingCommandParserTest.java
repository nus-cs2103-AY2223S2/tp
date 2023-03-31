
package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindMeetingCommandParserTest {
    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    private final String startDateTime = "09-11-2001 11:30";
    private final LocalDateTime date = LocalDateTime.of(2001, 11, 9, 11, 30);

    @Test
    public void parse_returnsCorrectMeeting() throws ParseException {
        assertEquals(parser.parse(startDateTime), new FindMeetingCommand(date));

    }

}