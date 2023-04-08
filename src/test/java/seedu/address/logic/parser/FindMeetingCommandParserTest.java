package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindMeetingCommandParserTest {
    private final FindMeetingCommandParser parser = new FindMeetingCommandParser();

    private final String startDate = "09-11-2001";
    private final LocalDate date = LocalDate.of(2001, 11, 9);

    @Test
    public void parse_returnsCorrectMeeting() throws ParseException {
        assertEquals(parser.parse(startDate), new FindMeetingCommand(date));
    }

}
