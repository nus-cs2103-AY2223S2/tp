package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindMeetingCommandParserTest {
    private final FindMeetingCommandParser psr = new FindMeetingCommandParser();

    private final String startDate = "meetingFind 09-11-2023";
    private final LocalDate date = LocalDate.of(2023, 11, 9);

    @Test
    public void parse_returnsCorrectMeeting() throws ParseException {
        assertEquals(psr.parse(startDate), new FindMeetingCommand(date));
    }

    @Test
    public void parse_wrongPersonIndex_failure() throws ParseException {
        assertParseFailure(psr, "meetingFind -5", "Please input a valid person index");
    }

    @Test
    public void parse_missingField_failure() {

        // no parameters
        assertParseFailure(psr, FindMeetingCommand.COMMAND_WORD, "Please input a valid date or person index!");

        // no index
        assertParseFailure(psr, FindMeetingCommand.COMMAND_WORD + " ", "Please input a valid date or person index!");


    }
}
