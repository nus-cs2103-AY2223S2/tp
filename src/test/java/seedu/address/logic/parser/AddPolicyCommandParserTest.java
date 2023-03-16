package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POLICY_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POLICY_FREQUENCY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POLICY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POLICY_PREMIUM;
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
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

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
    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "a" + " " + policyStub, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddPolicyCommand.MESSAGE_USAGE));
        // invalid policy name
        assertParseFailure(parser, "1" + " " + INVALID_POLICY_NAME + POLICY_DATE_AMY + POLICY_PREMIUM_AMY
                        + POLICY_FREQUENCY_AMY,
                String.format(PolicyName.MESSAGE_CONSTRAINTS));
        // invalid policy date
        assertParseFailure(parser, "1" + " " + POLICY_NAME_AMY + " " + INVALID_POLICY_DATE + POLICY_PREMIUM_AMY
                        + POLICY_FREQUENCY_AMY,
                String.format(CustomDate.MESSAGE_CONSTRAINTS));
        // invalid policy premium
        assertParseFailure(parser, "1" + " " + POLICY_NAME_AMY + " " + POLICY_DATE_AMY + " " + INVALID_POLICY_PREMIUM
                        + POLICY_FREQUENCY_AMY,
                String.format(Premium.MESSAGE_CONSTRAINTS));
        // invalid policy frequency
        assertParseFailure(parser, "1" + " " + POLICY_NAME_AMY + " " + POLICY_DATE_AMY + " " + POLICY_PREMIUM_AMY
                        + " " + INVALID_POLICY_FREQUENCY,
                String.format(Frequency.MESSAGE_CONSTRAINTS));
    }
}
