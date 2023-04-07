package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortEventCommand;
import seedu.address.testutil.TypicalSortEventKeys;

public class SortEventCommandParserTest {

    private SortEventCommandParser parser = new SortEventCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_returnsSortEventCommand() {
        SortEventCommand expectedSortEventCommand = new SortEventCommand(SortEventKey.SORT_BY_START_DATE_TIME);
        assertParseSuccess(parser, TypicalSortEventKeys.SORT_BY_START_DATE_TIME, expectedSortEventCommand);
    }
}
