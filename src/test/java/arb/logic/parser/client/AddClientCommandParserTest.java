package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.EMAIL_DESC_ALIAS_AMY;
import static arb.logic.commands.CommandTestUtil.EMAIL_DESC_ALIAS_BOB;
import static arb.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static arb.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static arb.logic.commands.CommandTestUtil.EMPTY_EMAIL;
import static arb.logic.commands.CommandTestUtil.EMPTY_EMAIL_ALIAS;
import static arb.logic.commands.CommandTestUtil.EMPTY_PHONE;
import static arb.logic.commands.CommandTestUtil.EMPTY_PHONE_ALIAS;
import static arb.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static arb.logic.commands.CommandTestUtil.NAME_DESC_ALIAS_AMY;
import static arb.logic.commands.CommandTestUtil.NAME_DESC_ALIAS_BOB;
import static arb.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static arb.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static arb.logic.commands.CommandTestUtil.PHONE_DESC_ALIAS_AMY;
import static arb.logic.commands.CommandTestUtil.PHONE_DESC_ALIAS_BOB;
import static arb.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static arb.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_FRIEND;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_HUSBAND;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static arb.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalClients.AMY;
import static arb.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import arb.logic.commands.client.AddClientCommand;
import arb.model.client.Client;
import arb.model.client.Email;
import arb.model.client.Name;
import arb.model.client.Phone;
import arb.model.tag.Tag;
import arb.testutil.ClientBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple names, main prefix only - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple names, alias prefix only - last name accepted
        assertParseSuccess(parser, NAME_DESC_ALIAS_AMY + NAME_DESC_ALIAS_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple names, mix of main prefixes and alias prefixes - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_ALIAS_AMY + NAME_DESC_ALIAS_BOB
                + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple phones, main prefixes only - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple phones, alias prefix only - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_ALIAS_AMY + PHONE_DESC_ALIAS_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple phones, mix of main prefix and alias prefix - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_ALIAS_AMY + PHONE_DESC_ALIAS_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple emails, main prefix only - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple emails, alias prefix only - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_ALIAS_AMY + EMAIL_DESC_ALIAS_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple emails, mix of main prefix and alias prefix - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_ALIAS_AMY + EMAIL_DESC_AMY
                + EMAIL_DESC_ALIAS_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple tags, main prefix only - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddClientCommand(expectedClientMultipleTags));

        // multiple tags, alias prefix only - all accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_ALIAS_HUSBAND + TAG_DESC_ALIAS_FRIEND, new AddClientCommand(expectedClientMultipleTags));

        // multiple tags, mix of main prefix and alias prefix - all accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_ALIAS_HUSBAND + TAG_DESC_FRIEND, new AddClientCommand(expectedClientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddClientCommand(expectedClient));

        // no phone number
        expectedClient = new ClientBuilder(AMY).withPhone(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND,
                new AddClientCommand(expectedClient));

        // no email
        expectedClient = new ClientBuilder(AMY).withEmail(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + TAG_DESC_FRIEND,
                new AddClientCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyValue_success() {
        // empty phone, main prefix
        Client expectedClient = new ClientBuilder(BOB).withPhone(null).build();
        assertParseSuccess(parser, NAME_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + EMAIL_DESC_BOB
                + EMPTY_PHONE,
                new AddClientCommand(expectedClient));

        // empty phone, alias prefix
        assertParseSuccess(parser, NAME_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + EMAIL_DESC_BOB
                + EMPTY_PHONE_ALIAS,
                new AddClientCommand(expectedClient));

        // empty email, main prefix
        expectedClient = new ClientBuilder(BOB).withEmail(null).build();
        assertParseSuccess(parser, NAME_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PHONE_DESC_BOB
                + EMPTY_EMAIL,
                new AddClientCommand(expectedClient));

        // empty email, alias prefix
        assertParseSuccess(parser, NAME_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PHONE_DESC_BOB
                + EMPTY_EMAIL_ALIAS,
                new AddClientCommand(expectedClient));
    }
}
