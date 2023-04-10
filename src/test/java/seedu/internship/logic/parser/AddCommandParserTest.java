package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.COMMENT_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.COMMENT_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_COMMENT_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internship.logic.commands.CommandTestUtil.ROLE_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_BACK;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_FRONT;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FRONT;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalInternships.APPLE;
import static seedu.internship.testutil.TypicalInternships.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;
import seedu.internship.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags(VALID_TAG_FRONT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE
                + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE
                + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));

        // multiple roles - last role accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_APPLE + ROLE_DESC_GOOGLE
                + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_APPLE
                + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));

        // multiple dates - last date accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_APPLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));

        // multiple comments - last comment accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + COMMENT_DESC_APPLE + COMMENT_DESC_GOOGLE + TAG_DESC_FRONT,
                new AddCommand(expectedInternship));


        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(GOOGLE)
                .withTags(VALID_TAG_FRONT, VALID_TAG_BACK).build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT,
                new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        //zero comments
        Internship expectedInternship = new InternshipBuilder(APPLE).withComment("NA").build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + STATUS_DESC_APPLE
                + DATE_DESC_APPLE + TAG_DESC_FRONT, new AddCommand(expectedInternship));

        //zero tags
        expectedInternship = new InternshipBuilder(APPLE).withTags().build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + STATUS_DESC_APPLE
                        + DATE_DESC_APPLE + COMMENT_DESC_APPLE, new AddCommand(expectedInternship));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company name prefix
        assertParseFailure(parser, VALID_COMPANY_NAME_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                        + DATE_DESC_GOOGLE, expectedMessage);

        // missing role prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + VALID_ROLE_GOOGLE + STATUS_DESC_GOOGLE
                        + ROLE_DESC_GOOGLE, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + VALID_STATUS_GOOGLE
                        + DATE_DESC_GOOGLE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                        + VALID_DATE_GOOGLE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_NAME_GOOGLE + VALID_ROLE_GOOGLE + VALID_STATUS_GOOGLE
                        + VALID_DATE_GOOGLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company name
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT,
                CompanyName.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + INVALID_ROLE_DESC + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, Role.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + INVALID_STATUS_DESC
                + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, Status.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + INVALID_DATE_DESC + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, Date.MESSAGE_CONSTRAINTS);

        // invalid comment
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + INVALID_COMMENT_DESC + TAG_DESC_BACK + TAG_DESC_FRONT,
                Comment.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                        + INVALID_DATE_DESC, CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE
                        + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + COMMENT_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
