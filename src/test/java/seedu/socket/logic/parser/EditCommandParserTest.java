package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_PROFILE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.LANGUAGE_DESC_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.LANGUAGE_DESC_PYTHON;
import static seedu.socket.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.PROFILE_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PROFILE_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.socket.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_GITHUBPROFILE_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_GITHUBPROFILE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_PYTHON;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.EditCommand;
import seedu.socket.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Phone;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;
import seedu.socket.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {
    private static final String NAME_EMPTY = " " + PREFIX_NAME;
    private static final String PROFILE_EMPTY = " " + PREFIX_PROFILE;
    private static final String PHONE_EMPTY = " " + PREFIX_PHONE;
    private static final String EMAIL_EMPTY = " " + PREFIX_EMAIL;
    private static final String ADDRESS_EMPTY = " " + PREFIX_ADDRESS;
    private static final String LANGUAGE_EMPTY = " " + PREFIX_LANGUAGE;
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_PROFILE_DESC, GitHubProfile.MESSAGE_CONSTRAINTS); // invalid profile
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_LANGUAGE_DESC, Language.MESSAGE_CONSTRAINTS); // invalid language
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_LANGUAGE} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid language results in error
        assertParseFailure(parser, "1" + LANGUAGE_DESC_PYTHON + LANGUAGE_EMPTY, Language.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + LANGUAGE_DESC_PYTHON + LANGUAGE_EMPTY + LANGUAGE_DESC_CPLUSPLUS,
                Language.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + LANGUAGE_EMPTY + LANGUAGE_DESC_PYTHON + LANGUAGE_DESC_CPLUSPLUS,
                Language.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_PROFILE_DESC + INVALID_EMAIL_DESC
                + VALID_ADDRESS_AMY + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyNonLanguageTagField_failure() {
        assertParseFailure(parser, "1" + NAME_EMPTY, Name.MESSAGE_CONSTRAINTS); // empty name
        assertParseFailure(parser, "1" + PROFILE_EMPTY, GitHubProfile.MESSAGE_CONSTRAINTS); // empty profile
        assertParseFailure(parser, "1" + PHONE_EMPTY, Phone.MESSAGE_CONSTRAINTS); // empty phone
        assertParseFailure(parser, "1" + EMAIL_EMPTY, Email.MESSAGE_CONSTRAINTS); // empty email
        assertParseFailure(parser, "1" + ADDRESS_EMPTY, Address.MESSAGE_CONSTRAINTS); // empty address
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + PROFILE_DESC_AMY + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withProfile(VALID_GITHUBPROFILE_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withLanguages(VALID_LANGUAGE_CPLUSPLUS)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // profile
        userInput = targetIndex.getOneBased() + PROFILE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withProfile(VALID_GITHUBPROFILE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // languages
        userInput = targetIndex.getOneBased() + LANGUAGE_DESC_PYTHON;
        descriptor = new EditPersonDescriptorBuilder().withLanguages(VALID_LANGUAGE_PYTHON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PROFILE_DESC_AMY + LANGUAGE_DESC_CPLUSPLUS + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + LANGUAGE_DESC_PYTHON + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_HUSBAND + PROFILE_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withProfile(VALID_GITHUBPROFILE_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withLanguages(VALID_LANGUAGE_CPLUSPLUS, VALID_LANGUAGE_PYTHON)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetLanguages_failure() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + LANGUAGE_EMPTY;

        assertParseFailure(parser, userInput, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
