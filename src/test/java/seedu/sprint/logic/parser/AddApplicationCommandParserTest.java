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
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.TAG_DESC_HIGHSALARY;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.TAG_DESC_SCHOOL;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_TAG_HIGHSALARY;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;
import static seedu.sprint.testutil.TypicalApplications.BYTEDANCE;
import static seedu.sprint.testutil.TypicalApplications.GRAB;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.tag.Tag;
import seedu.sprint.testutil.ApplicationBuilder;

public class AddApplicationCommandParserTest {
    private AddApplicationCommandParser parser = new AddApplicationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(BYTEDANCE).withTags(VALID_TAG_HIGHSALARY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY, new AddApplicationCommand(expectedApplication));

        // multiple names - last companyName accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GRAB + COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY, new AddApplicationCommand(expectedApplication));

        // multiple phones - last status accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_GRAB + STATUS_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY, new AddApplicationCommand(expectedApplication));

        // multiple emails - last companyEmail accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_GRAB + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY, new AddApplicationCommand(expectedApplication));

        // multiple addresses - last role accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE
                + COMPANY_EMAIL_DESC_BYTEDANCE + ROLE_DESC_GRAB
                + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY, new AddApplicationCommand(expectedApplication));

        // multiple tags - all accepted
        Application expectedApplicationMultipleTags = new ApplicationBuilder(BYTEDANCE)
                .withTags(VALID_TAG_HIGHSALARY, VALID_TAG_SCHOOL)
                .build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE
                + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL, new AddApplicationCommand(expectedApplicationMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Application expectedApplication = new ApplicationBuilder(GRAB).withTags().build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_GRAB + STATUS_DESC_GRAB + COMPANY_EMAIL_DESC_GRAB
                        + ROLE_DESC_GRAB,
                new AddApplicationCommand(expectedApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicationCommand.MESSAGE_USAGE);

        // missing companyName prefix
        assertParseFailure(parser, VALID_COMPANY_NAME_BYTEDANCE + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                        + ROLE_DESC_BYTEDANCE,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + VALID_STATUS_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                        + ROLE_DESC_BYTEDANCE,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + VALID_COMPANY_EMAIL_BYTEDANCE
                        + ROLE_DESC_BYTEDANCE,
                expectedMessage);

        // missing role prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                        + VALID_ROLE_BYTEDANCE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_NAME_BYTEDANCE + VALID_STATUS_BYTEDANCE + VALID_COMPANY_EMAIL_BYTEDANCE
                        + VALID_ROLE_BYTEDANCE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid companyName
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE
                + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + INVALID_STATUS_DESC + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE
                + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL, Status.MESSAGE_CONSTRAINTS);

        // invalid companyEmail
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + INVALID_COMPANY_EMAIL_DESC
                + ROLE_DESC_BYTEDANCE
                + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL, CompanyEmail.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                + INVALID_ROLE_DESC
                + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL, Role.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                + ROLE_DESC_BYTEDANCE
                + INVALID_TAG_DESC + VALID_TAG_SCHOOL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + STATUS_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                        + INVALID_ROLE_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_BYTEDANCE + STATUS_DESC_BYTEDANCE
                        + COMPANY_EMAIL_DESC_BYTEDANCE + ROLE_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY + TAG_DESC_SCHOOL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicationCommand.MESSAGE_USAGE));
    }
}
