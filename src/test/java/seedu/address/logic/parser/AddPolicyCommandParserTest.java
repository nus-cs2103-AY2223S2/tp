package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPolicyCommand;


class AddPolicyCommandParserTest {
    private AddPolicyCommandParser parser = new AddPolicyCommandParser();
    private final String policyName = "Health insurance";
    @Test
    public void parse_indexSpecified_success() {
        // have policyName
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY_NAME + policyName;
        AddPolicyCommand expectedCommand = new AddPolicyCommand(targetIndex, policyName);
        assertParseSuccess(parser, userInput, expectedCommand);
        // no policyName
        userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY_NAME;
        expectedCommand = new AddPolicyCommand(targetIndex, "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE);
        // no index specified
        assertParseFailure(parser, AddPolicyCommand.COMMAND_WORD + " " + policyName, expectedMessage);
        // no parameters
        assertParseFailure(parser, AddPolicyCommand.COMMAND_WORD, expectedMessage);
    }
}
