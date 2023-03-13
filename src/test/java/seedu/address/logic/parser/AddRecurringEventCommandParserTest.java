package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.SampleEventUtil.INVALID_TIME_RECURRING_EVENT;
import static seedu.address.testutil.SampleEventUtil.MISSING_DAY_RECURRING_EVENT;
import static seedu.address.testutil.SampleEventUtil.MISSING_INDEX_RECURRING_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecurringEventCommand;

public class AddRecurringEventCommandParserTest {
    @Test
    public void parse_invalidValue_failure() {

        AddRecurringEventCommandParser parser = new AddRecurringEventCommandParser();

        String expected1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurringEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, MISSING_INDEX_RECURRING_EVENT, expected1);
        assertParseFailure(parser, MISSING_DAY_RECURRING_EVENT, expected1);
        assertParseFailure(parser, INVALID_TIME_RECURRING_EVENT, expected1);

    }

}
