package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALEX;
import static seedu.address.testutil.TypicalPersons.BEN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BEN).withGroupTags(CommandTestUtil.VALID_GROUP_2).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BEN
                + CommandTestUtil.PHONE_DESC_BEN + CommandTestUtil.EMAIL_DESC_BEN
                + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_ALEX + CommandTestUtil.NAME_DESC_BEN
                + CommandTestUtil.PHONE_DESC_BEN + CommandTestUtil.EMAIL_DESC_BEN
                + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_ALEX
                + CommandTestUtil.PHONE_DESC_BEN + CommandTestUtil.EMAIL_DESC_BEN
                + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_ALEX + CommandTestUtil.EMAIL_DESC_BEN
                + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_ALEX
                + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BEN)
                .withGroupTags(CommandTestUtil.VALID_GROUP_2, CommandTestUtil.VALID_GROUP_1)
                .build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC
                + CommandTestUtil.VALID_GROUP_2_DESC, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(ALEX).withGroupTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_ALEX
                        + CommandTestUtil.PHONE_DESC_ALEX
                        + CommandTestUtil.EMAIL_DESC_ALEX + CommandTestUtil.ADDRESS_DESC_ALEX
                        + CommandTestUtil.TELEGRAM_DESC_ALEX,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.NAME_BEN + CommandTestUtil.PHONE_DESC_BEN
                        + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_BEN
                        + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                        + CommandTestUtil.EMAIL_BEN + CommandTestUtil.ADDRESS_DESC_BEN,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                        + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_BEN,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.NAME_BEN + CommandTestUtil.PHONE_BEN
                        + CommandTestUtil.EMAIL_BEN + CommandTestUtil.ADDRESS_BEN,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.INVALID_EMAIL_DESC + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.INVALID_ADDRESS_DESC
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC,
                Address.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.INVALID_TELEGRAM_DESC
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC,
                TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.INVALID_GROUP_DESC + CommandTestUtil.VALID_GROUP_2
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.VALID_MODULE_2_DESC,
                GroupTag.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BEN + CommandTestUtil.PHONE_DESC_BEN
                + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.ADDRESS_DESC_BEN
                + CommandTestUtil.TELEGRAM_DESC_BEN
                + CommandTestUtil.VALID_GROUP_1_DESC + CommandTestUtil.VALID_GROUP_2_DESC
                + CommandTestUtil.VALID_MODULE_1_DESC + CommandTestUtil.INVALID_MODULE_1_DESC,
                ModuleTag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BEN
                        + CommandTestUtil.EMAIL_DESC_BEN + CommandTestUtil.INVALID_ADDRESS_DESC
                        + CommandTestUtil.TELEGRAM_DESC_BEN,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_BEN
                        + CommandTestUtil.PHONE_DESC_BEN + CommandTestUtil.EMAIL_DESC_BEN
                        + CommandTestUtil.TELEGRAM_DESC_BEN
                        + CommandTestUtil.ADDRESS_DESC_BEN + CommandTestUtil.VALID_GROUP_1_DESC
                        + CommandTestUtil.VALID_GROUP_2_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
