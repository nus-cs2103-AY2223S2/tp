package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveMeetingCommand;

public class RemoveMeetingParserTest {
    private RemoveMeetingCommandParser parser = new RemoveMeetingCommandParser();

    @Test
    public void parse_indexSpecified_success() {

        String userInput = " 1 1";
        RemoveMeetingCommand expectedCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
            INDEX_FIRST_MEETING);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemoveMeetingCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemoveMeetingCommand.COMMAND_WORD + " 1", expectedMessage);
    }
}
