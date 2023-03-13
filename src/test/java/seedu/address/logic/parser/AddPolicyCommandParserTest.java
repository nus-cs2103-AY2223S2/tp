package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_FREQUENCY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_PREMIUM_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalPolicies.AMY_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPolicyCommand;

class AddPolicyCommandParserTest {
    private final AddPolicyCommandParser parser = new AddPolicyCommandParser();

    private final String policyStub = POLICY_NAME_AMY + POLICY_DATE_AMY + POLICY_PREMIUM_AMY + POLICY_FREQUENCY_AMY;


    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " " + policyStub;
        AddPolicyCommand expectedCommand = new AddPolicyCommand(targetIndex, AMY_POLICY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE);
        // no index specified
        assertParseFailure(parser, AddPolicyCommand.COMMAND_WORD + " " + policyStub, expectedMessage);
        // no parameters
        assertParseFailure(parser, AddPolicyCommand.COMMAND_WORD, expectedMessage);
    }
}
