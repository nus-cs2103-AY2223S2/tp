package seedu.address.logic.parser.timetable;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.AddCommandParser;
import seedu.address.model.person.Name;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_TIMETABLE_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.Assert.assertThrows;

public class TimetableParserTest {
    private TimetableParser parser = new TimetableParser();

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_TIMETABLE_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
