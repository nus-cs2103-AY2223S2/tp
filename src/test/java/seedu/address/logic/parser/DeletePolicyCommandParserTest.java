package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePolicyCommand;

class DeletePolicyCommandParserTest {

    private DeletePolicyCommandParser parser = new DeletePolicyCommandParser();
    @Test
    void parse_validArgs_returnsDeletePolicyCommand() {
        String userInput = "1 pi/1";
        DeletePolicyCommand expectedCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_invalidClientIndex_throwsIllegalValueException() {
        String userInput = "a pi/1";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    void parse_missingPolicyIndex_throwsParseException() {
        String userInput = "a 1";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    void parse_missingClientIndex_throwsParseException() {
        String userInput = "pi/1";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

}
