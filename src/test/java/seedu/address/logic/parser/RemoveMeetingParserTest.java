package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMeetingCommand;

public class RemoveMeetingParserTest {
    private RemoveMeetingCommandParser parser = new RemoveMeetingCommandParser();
    private final String SomeMeetingIndex = "0";

    @Test
    public void parse_indexSpecified_success() {
        // have MEETING
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + SomeMeetingIndex;
        RemoveMeetingCommand expectedCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
                Index.fromZeroBased(Integer.parseInt(SomeMeetingIndex)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no MEETING
        userInput = targetIndex.getOneBased() + SomeMeetingIndex;
        expectedCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
                Index.fromZeroBased(Integer.parseInt(SomeMeetingIndex)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemoveMeetingCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemoveMeetingCommand.COMMAND_WORD + " " + SomeMeetingIndex, expectedMessage);
    }
}
