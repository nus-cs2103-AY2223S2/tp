package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BACK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRONT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_NAME_APPLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        // invalid company name
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS);
        // invalid role
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS);
        // invalid status
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS);
        // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);
        // invalid role followed by valid status
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + STATUS_DESC_APPLE, Role.MESSAGE_CONSTRAINTS);
        // valid role followed by invalid role. The test case for invalid role followed by valid role
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ROLE_DESC_GOOGLE + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRONT + TAG_DESC_BACK + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRONT + TAG_EMPTY + TAG_DESC_BACK, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRONT + TAG_DESC_BACK, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_ROLE_DESC + VALID_STATUS_APPLE
                        + VALID_ROLE_APPLE, CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_GOOGLE + TAG_DESC_BACK
                + STATUS_DESC_APPLE + DATE_DESC_APPLE + COMPANY_NAME_DESC_APPLE + TAG_DESC_FRONT;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_APPLE).withRole(VALID_ROLE_GOOGLE)
                .withStatus(VALID_STATUS_APPLE).withDate(VALID_DATE_APPLE)
                .withTags(VALID_TAG_FRONT, VALID_TAG_BACK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_GOOGLE + STATUS_DESC_APPLE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_GOOGLE)
                .withStatus(VALID_STATUS_APPLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_APPLE;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_APPLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withStatus(VALID_STATUS_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withDate(VALID_DATE_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRONT;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_FRONT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_APPLE + STATUS_DESC_APPLE + DATE_DESC_APPLE
                + TAG_DESC_FRONT + ROLE_DESC_APPLE + DATE_DESC_APPLE + STATUS_DESC_APPLE + TAG_DESC_FRONT
                + ROLE_DESC_GOOGLE + DATE_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_BACK;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_GOOGLE)
                .withStatus(VALID_STATUS_GOOGLE).withDate(VALID_DATE_GOOGLE).withTags(VALID_TAG_FRONT, VALID_TAG_BACK)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + ROLE_DESC_GOOGLE;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withRole(VALID_ROLE_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + STATUS_DESC_GOOGLE + INVALID_ROLE_DESC + DATE_DESC_GOOGLE
                + ROLE_DESC_GOOGLE;
        descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
                .withDate(VALID_DATE_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
