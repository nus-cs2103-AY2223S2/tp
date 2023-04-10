package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.CUSTOMER_TYPE_DESC_INDIVIDUAL;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_CUSTOMER_TYPE;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.testutil.TypicalCustomers.AMY;
import static seedu.loyaltylift.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.AddCustomerCommand;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.testutil.CustomerBuilder;

public class AddCustomerCommandParserTest {
    private AddCustomerCommandParser parser = new AddCustomerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Customer expectedCustomer = new CustomerBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddCustomerCommand(expectedCustomer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddCustomerCommand(expectedCustomer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddCustomerCommand(expectedCustomer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddCustomerCommand(expectedCustomer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB, new AddCustomerCommand(expectedCustomer));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Customer expectedCustomer = new CustomerBuilder(AMY).build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + CUSTOMER_TYPE_DESC_INDIVIDUAL,
                new AddCustomerCommand(expectedCustomer));

        // no customer type, defaults to individual
        expectedCustomer = new CustomerBuilder(AMY).build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCustomerCommand(expectedCustomer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE));

        // invalid customer type
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_CUSTOMER_TYPE,
                CustomerType.MESSAGE_FAIL_CONVERSION);
    }
}
