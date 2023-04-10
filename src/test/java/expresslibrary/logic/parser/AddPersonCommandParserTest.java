package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static expresslibrary.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static expresslibrary.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static expresslibrary.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static expresslibrary.testutil.TypicalPersons.AMY;
import static expresslibrary.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import expresslibrary.logic.commands.AddPersonCommand;
import expresslibrary.model.person.Email;
import expresslibrary.model.person.Name;
import expresslibrary.model.person.Person;
import expresslibrary.model.person.Phone;
import expresslibrary.model.tag.Tag;
import expresslibrary.testutil.PersonBuilder;

public class AddPersonCommandParserTest {
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddPersonCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddPersonCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
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
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }
}
