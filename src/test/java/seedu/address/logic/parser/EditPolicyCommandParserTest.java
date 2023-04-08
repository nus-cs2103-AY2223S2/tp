package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPolicyCommand;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;
import seedu.address.testutil.EditPolicyDescriptorBuilder;

class EditPolicyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditPolicyCommand.MESSAGE_USAGE);

    private EditPolicyCommandParser parser = new EditPolicyCommandParser();
    @Test
    void parse_missingParts_failure() {
        assertParseFailure(parser, VALID_POLICY_NAME_AMY, MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "1 pi/1", EditPolicyCommand.MESSAGE_NOT_EDITED);

        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, "-5 pi/1", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "0 pi/1", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "1 pi/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 pi/1" + INVALID_POLICY_NAME, PolicyName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 pi/1 pn/Testing", PolicyName.MESSAGE_CONSTRAINTS_ENUM);
        assertParseFailure(parser, "1 pi/1" + INVALID_POLICY_DATE, CustomDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 pi/1" + INVALID_POLICY_FREQUENCY, Frequency.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 pi/1" + INVALID_POLICY_PREMIUM, Premium.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " pi/1" + POLICY_NAME_AMY + POLICY_DATE_AMY + POLICY_PREMIUM_AMY
                + POLICY_FREQUENCY_AMY;

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).withStartDate(VALID_POLICY_DATE_AMY)
                .withPremium(VALID_POLICY_PREMIUM_AMY).withFrequency(VALID_POLICY_FREQUENCY_AMY).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);

        assertParseSuccess(parser, userInput, editPolicyCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = INDEX_FIRST_CLIENT.getOneBased() + " pi/1" + POLICY_NAME_AMY + POLICY_PREMIUM_AMY;

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).withPremium(VALID_POLICY_PREMIUM_AMY).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY, descriptor);

        assertParseSuccess(parser, userInput, editPolicyCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;

        String userInput = targetIndex.getOneBased() + " pi/1" + POLICY_NAME_AMY;

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).build();

        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);
        assertParseSuccess(parser, userInput, editPolicyCommand);

        userInput = targetIndex.getOneBased() + " pi/1" + POLICY_DATE_AMY;
        descriptor = new EditPolicyDescriptorBuilder()
                .withStartDate(VALID_POLICY_DATE_AMY).build();

        editPolicyCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);
        assertParseSuccess(parser, userInput, editPolicyCommand);


        userInput = targetIndex.getOneBased() + " pi/1" + POLICY_PREMIUM_AMY;
        descriptor = new EditPolicyDescriptorBuilder()
                .withPremium(VALID_POLICY_PREMIUM_AMY).build();

        editPolicyCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);
        assertParseSuccess(parser, userInput, editPolicyCommand);


        userInput = targetIndex.getOneBased() + " pi/1" + POLICY_FREQUENCY_AMY;
        descriptor = new EditPolicyDescriptorBuilder()
                .withFrequency(VALID_POLICY_FREQUENCY_AMY).build();

        editPolicyCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);
        assertParseSuccess(parser, userInput, editPolicyCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " pi/1"
                + POLICY_NAME_AMY + POLICY_FREQUENCY_AMY + POLICY_NAME_BOB
                + POLICY_FREQUENCY_BOB;

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_BOB).withFrequency(VALID_POLICY_FREQUENCY_BOB).build();
        EditPolicyCommand expectedCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " pi/1" + INVALID_POLICY_NAME + POLICY_NAME_AMY;

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).build();
        EditPolicyCommand expectedCommand = new EditPolicyCommand(targetIndex, INDEX_FIRST_POLICY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
