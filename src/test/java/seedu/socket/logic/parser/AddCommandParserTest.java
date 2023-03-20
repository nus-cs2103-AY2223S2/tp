package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_PROFILE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.LANGUAGE_DESC_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.LANGUAGE_DESC_PYTHON;
import static seedu.socket.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.socket.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.socket.logic.commands.CommandTestUtil.PROFILE_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PROFILE_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.socket.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_GITHUBPROFILE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_PYTHON;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalPersons.AMY;
import static seedu.socket.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.AddCommand;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.Phone;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;
import seedu.socket.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple profiles - last profile accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_AMY + PROFILE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple languages - all accepted
        Person expectedPersonMultipleLanguages = new PersonBuilder(BOB).withLanguages(VALID_LANGUAGE_PYTHON,
                VALID_LANGUAGE_CPLUSPLUS).withTags().build();
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_PYTHON + LANGUAGE_DESC_CPLUSPLUS,
                new AddCommand(expectedPersonMultipleLanguages));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PROFILE_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + LANGUAGE_DESC_PYTHON, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GITHUBPROFILE_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid profile
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PROFILE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                GitHubProfile.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid language
        assertParseFailure(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_LANGUAGE_DESC + TAG_DESC_FRIEND, Language.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PROFILE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PROFILE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
