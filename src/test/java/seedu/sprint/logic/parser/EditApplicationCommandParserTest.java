package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_EMAIL_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_EMAIL_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_NAME_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_NAME_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_COMPANY_EMAIL_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_ROLE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_TAG_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.TAG_DESC_HIGHSALARY;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.TAG_DESC_SCHOOL;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_GRAB;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_THIRD_APPLICATION;
import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.commands.EditApplicationCommand;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.tag.Tag;
import seedu.sprint.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationCommandParserTest {

    private EditApplicationCommandParser parser = new EditApplicationCommandParser();

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditApplicationCommand.MESSAGE_USAGE);


    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_NAME_BYTEDANCE, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditApplicationCommand.MESSAGE_NOT_EDITED);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_NAME_DESC_BYTEDANCE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_NAME_DESC_BYTEDANCE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random strong", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC,
                CompanyName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_COMPANY_EMAIL_DESC,
                CompanyEmail.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid name followed by valid email
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + COMPANY_EMAIL_DESC_GRAB,
                CompanyName.MESSAGE_CONSTRAINTS);

        // valid email followed by invalid email. The test case for invalid email followed by valid email
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + COMPANY_EMAIL_DESC_GRAB + INVALID_COMPANY_EMAIL_DESC,
                CompanyEmail.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_SCHOOL + TAG_EMPTY + TAG_DESC_HIGHSALARY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_SCHOOL + TAG_DESC_HIGHSALARY, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_COMPANY_EMAIL_DESC +
                        STATUS_DESC_GRAB + COMPANY_EMAIL_DESC_GRAB, CompanyName.MESSAGE_CONSTRAINTS);
    }
    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_BYTEDANCE
                + COMPANY_NAME_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE
                + STATUS_DESC_BYTEDANCE;

        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withRole(VALID_ROLE_BYTEDANCE)
                .withCompanyName(VALID_COMPANY_NAME_BYTEDANCE).withCompanyEmail(VALID_COMPANY_EMAIL_BYTEDANCE)
                .withStatus(VALID_STATUS_BYTEDANCE).build();

        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE;

        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BYTEDANCE)
                .withStatus(VALID_STATUS_BYTEDANCE).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company name
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_GRAB;
        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GRAB).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_BYTEDANCE;
        descriptor = new EditApplicationDescriptorBuilder().withRole(VALID_ROLE_BYTEDANCE).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company email
        userInput = targetIndex.getOneBased() + COMPANY_EMAIL_DESC_GRAB;
        descriptor = new EditApplicationDescriptorBuilder().withCompanyEmail(VALID_COMPANY_EMAIL_GRAB).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_GRAB;
        descriptor = new EditApplicationDescriptorBuilder().withStatus(VALID_STATUS_GRAB).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_BYTEDANCE + COMPANY_NAME_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + ROLE_DESC_GRAB
                + COMPANY_NAME_DESC_GRAB + COMPANY_EMAIL_DESC_GRAB + STATUS_DESC_GRAB;

        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withRole(VALID_ROLE_GRAB).withCompanyName(VALID_COMPANY_NAME_GRAB)
                .withCompanyEmail(VALID_COMPANY_EMAIL_GRAB).withStatus(VALID_STATUS_GRAB).build();

        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_COMPANY_NAME_DESC + COMPANY_NAME_DESC_BYTEDANCE;
        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BYTEDANCE).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + ROLE_DESC_GRAB + INVALID_COMPANY_NAME_DESC + STATUS_DESC_GRAB
                + COMPANY_NAME_DESC_GRAB;
        descriptor = new EditApplicationDescriptorBuilder().withRole(VALID_ROLE_GRAB)
                .withStatus(VALID_STATUS_GRAB)
                .withCompanyName(VALID_COMPANY_NAME_GRAB).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }




}
