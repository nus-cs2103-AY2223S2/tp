package seedu.address.logic.parser.timetable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_TIMETABLE_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.timetable.TimetableCompletedCommand;
import seedu.address.logic.commands.timetable.TimetableDateCommand;
import seedu.address.logic.commands.timetable.TimetableUnscheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TimetableParserTest {
    private TimetableParser parser = new TimetableParser();

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_TIMETABLE_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_timetableDate() throws Exception {
        //assertTrue(parser.parseCommand(TimetableDateCommand.COMMAND_WORD) instanceof TimetableDateCommand);
        assertTrue(parser.parseCommand(TimetableDateCommand.COMMAND_WORD
                + " date/2023-03-03") instanceof TimetableDateCommand);
    }

    @Test
    public void parseCommand_timetableUnschedule() throws Exception {
        assertTrue(parser.parseCommand(TimetableUnscheduleCommand.COMMAND_WORD) instanceof TimetableUnscheduleCommand);
        assertTrue(parser.parseCommand(TimetableUnscheduleCommand.COMMAND_WORD
                + " 3") instanceof TimetableUnscheduleCommand);
    }

    @Test
    public void parseCommand_timetableComplete() throws Exception {
        assertTrue(parser.parseCommand(TimetableCompletedCommand.COMMAND_WORD) instanceof TimetableCompletedCommand);
        assertTrue(parser.parseCommand(TimetableCompletedCommand.COMMAND_WORD
                + " 3") instanceof TimetableCompletedCommand);
    }


}
