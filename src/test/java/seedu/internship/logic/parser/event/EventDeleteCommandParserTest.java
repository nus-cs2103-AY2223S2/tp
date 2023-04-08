package seedu.internship.logic.parser.event;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.event.EventDeleteCommand;

public class EventDeleteCommandParserTest {

    private EventDeleteCommandParser parser = new EventDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new EventDeleteCommand(INDEX_FIRST_INTERNSHIP));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EventDeleteCommand.MESSAGE_USAGE));
    }

}
