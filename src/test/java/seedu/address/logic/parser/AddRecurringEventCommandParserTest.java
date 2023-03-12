package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecurringEventCommand;

public class AddRecurringEventCommandParserTest {
    @Test
    public void parse_invalidValue_failure() {
        String missingIndex = "re/test1 d/Monday f/18:00 t/20:00";
        String missingDay = "1 re/test2 f/18:00 t/20:00";
        String missingTime = "1 re/test1 d/Monday t/20";

        AddRecurringEventCommandParser parser = new AddRecurringEventCommandParser();

        String expected1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurringEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, missingIndex, expected1);
        assertParseFailure(parser, missingDay, expected1);
        assertParseFailure(parser, missingTime, expected1);

    }

}
