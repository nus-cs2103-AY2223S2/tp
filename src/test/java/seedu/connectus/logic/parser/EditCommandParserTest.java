package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_DESC_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_DESC_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_POSITION_DESC_DIRECTOR;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_POSITION_DESC_PRESIDENT;
import static seedu.connectus.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_CCA_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_CCA_POSITION_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.MODULE_DESC_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.MODULE_DESC_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.connectus.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_POSITION_DIRECTOR;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_POSITION_PRESIDENT;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.EditCommand;
import seedu.connectus.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;
import seedu.connectus.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MODULE_EMPTY = " " + PREFIX_MODULE;
    private static final String CCA_EMPTY = " " + PREFIX_CCA;
    private static final String CCA_POSITION_EMPTY = " " + PREFIX_CCA_POSITION;

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
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_CCA_DESC, Cca.MESSAGE_CONSTRAINTS); // invalid cca tag
        assertParseFailure(parser, "1" + INVALID_CCA_POSITION_DESC,
                CcaPosition.MESSAGE_CONSTRAINTS); // invalid cca Position tag


        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MODULE} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + MODULE_DESC_CS2101 + MODULE_DESC_CS2103T + MODULE_EMPTY,
                Module.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MODULE_DESC_CS2103T + MODULE_EMPTY + MODULE_DESC_CS2101,
                Module.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MODULE_EMPTY + MODULE_DESC_CS2103T + MODULE_DESC_CS2101,
                Module.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_CCA} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + CCA_DESC_NES + CCA_DESC_ICS + CCA_EMPTY,
                Cca.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CCA_DESC_NES + CCA_EMPTY + CCA_DESC_ICS,
                Cca.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CCA_EMPTY + CCA_DESC_NES + CCA_DESC_ICS,
                Cca.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_CCA_POSITION} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + CCA_POSITION_DESC_PRESIDENT + CCA_POSITION_DESC_DIRECTOR + CCA_POSITION_EMPTY,
                CcaPosition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CCA_POSITION_DESC_PRESIDENT + CCA_POSITION_EMPTY + CCA_POSITION_DESC_DIRECTOR,
                CcaPosition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CCA_POSITION_EMPTY + CCA_POSITION_DESC_PRESIDENT + CCA_POSITION_DESC_DIRECTOR,
                CcaPosition.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + MODULE_DESC_CS2103T
                + TAG_DESC_HUSBAND + CCA_DESC_NES + CCA_POSITION_DESC_PRESIDENT
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + MODULE_DESC_CS2101 + CCA_DESC_ICS + CCA_POSITION_DESC_DIRECTOR;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withModules(VALID_MODULE_CS2103T, VALID_MODULE_CS2101)
                .withCcas(VALID_CCA_ICS, VALID_CCA_NES)
                .withCcaPositions(VALID_CCA_POSITION_DIRECTOR, VALID_CCA_POSITION_PRESIDENT)
                .build();
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

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //modules
        userInput = targetIndex.getOneBased() + MODULE_DESC_CS2103T;
        descriptor = new EditPersonDescriptorBuilder().withModules(VALID_MODULE_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //ccas
        userInput = targetIndex.getOneBased() + CCA_DESC_ICS;
        descriptor = new EditPersonDescriptorBuilder().withCcas(VALID_CCA_ICS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //ccaPositions
        userInput = targetIndex.getOneBased() + CCA_POSITION_DESC_DIRECTOR;
        descriptor = new EditPersonDescriptorBuilder().withCcaPositions(VALID_CCA_POSITION_DIRECTOR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + MODULE_DESC_CS2103T + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + MODULE_DESC_CS2101 + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + MODULE_DESC_CS2103T + CCA_DESC_ICS + CCA_POSITION_DESC_PRESIDENT;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withModules(VALID_MODULE_CS2101, VALID_MODULE_CS2103T).withCcas(VALID_CCA_ICS, VALID_CCA_NES)
                .withCcaPositions(VALID_CCA_POSITION_DIRECTOR, VALID_CCA_POSITION_PRESIDENT).build();
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
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetModules_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + MODULE_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withModules().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetCcas_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + CCA_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withCcas().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetCcaPositions_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + CCA_POSITION_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withCcaPositions().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
