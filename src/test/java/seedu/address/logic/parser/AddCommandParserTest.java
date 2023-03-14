package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last pay rate accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_AMY
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple sessions - last session accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_AMY + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB
                + TAG_DESC_HUSBAND + SESSION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + PAY_RATE_DESC_AMY + SESSION_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + VALID_PAY_RATE_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB + PAY_RATE_DESC_BOB ,
                expectedMessage);

        // missing session prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB + VALID_SESSION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ADDRESS_BOB + VALID_PAY_RATE_BOB + VALID_SESSION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB
                + SESSION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB
                + SESSION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + INVALID_PAY_RATE_DESC
                + SESSION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, PayRate.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + PAY_RATE_DESC_BOB
                + SESSION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB
                + SESSION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid session
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_RATE_DESC_BOB
                + INVALID_SESSION_DESC + VALID_TAG_FRIEND, Session.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + PAY_RATE_DESC_BOB + SESSION_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + PAY_RATE_DESC_BOB + SESSION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
