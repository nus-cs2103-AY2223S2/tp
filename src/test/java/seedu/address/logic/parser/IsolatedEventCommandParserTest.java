package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.SampleEventUtil.INVALID_DATE_FORMAT_ISOLATED_EVENT;
import static seedu.address.testutil.SampleEventUtil.MISSING_EVENT_NAME_ISOLATED_EVENT;
import static seedu.address.testutil.SampleEventUtil.MISSING_INDEX_ISOLATED_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddIsolatedEventCommand;

public class IsolatedEventCommandParserTest {
    private AddIsolatedEventCommandParser parser = new AddIsolatedEventCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIsolatedEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, MISSING_INDEX_ISOLATED_EVENT, expectedMessage);

        assertParseFailure(parser, MISSING_EVENT_NAME_ISOLATED_EVENT, expectedMessage);

        assertParseFailure(parser, INVALID_DATE_FORMAT_ISOLATED_EVENT, expectedMessage);
    }
}
