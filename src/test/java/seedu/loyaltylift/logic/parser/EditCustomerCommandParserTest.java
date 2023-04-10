package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.CUSTOMER_TYPE_DESC_ENTERPRISE;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.CUSTOMER_TYPE_DESC_INDIVIDUAL;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.EditCustomerCommand;
import seedu.loyaltylift.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.testutil.EditCustomerDescriptorBuilder;

public class EditCustomerCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCustomerCommand.MESSAGE_USAGE);

    private EditCustomerCommandParser parser = new EditCustomerCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCustomerCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + CUSTOMER_TYPE_DESC_ENTERPRISE;

        EditCustomerCommand.EditCustomerDescriptor descriptor =
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withCustomerType(CustomerType.ENTERPRISE).build();
        EditCustomerCommand expectedCommand = new EditCustomerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditCustomerCommand.EditCustomerDescriptor descriptor =
                new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCustomerCommand expectedCommand = new EditCustomerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCustomerCommand.EditCustomerDescriptor descriptor =
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCustomerCommand expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // customer type
        userInput = targetIndex.getOneBased() + CUSTOMER_TYPE_DESC_ENTERPRISE;
        descriptor = new EditCustomerDescriptorBuilder().withCustomerType(CustomerType.ENTERPRISE).build();
        expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + CUSTOMER_TYPE_DESC_INDIVIDUAL
                + CUSTOMER_TYPE_DESC_ENTERPRISE;

        EditCustomerCommand.EditCustomerDescriptor descriptor =
                new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCustomerType(CustomerType.ENTERPRISE).build();
        EditCustomerCommand expectedCommand = new EditCustomerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCustomerCommand expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + CUSTOMER_TYPE_DESC_ENTERPRISE;
        descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withCustomerType(CustomerType.ENTERPRISE).build();
        expectedCommand = new EditCustomerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
