package seedu.techtrack.logic.parser;

import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.CONTACT_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.CONTACT_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.EXPERIENCE_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.EXPERIENCE_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_EXPERIENCE_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_JOBDESCRIPTION_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.techtrack.logic.commands.CommandTestUtil.JOBDESCRIPTION_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.JOBDESCRIPTION_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.techtrack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.techtrack.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.techtrack.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_JOBDESCRIPTION_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.techtrack.logic.commands.CommandTestUtil.WEBSITE_DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.WEBSITE_DESC_BOB;
import static seedu.techtrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.techtrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.techtrack.testutil.TypicalRoles.AMY;
import static seedu.techtrack.testutil.TypicalRoles.BOB;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.commands.AddCommand;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;
import seedu.techtrack.testutil.RoleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Role expectedRole = new RoleBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple phones - last contact accepted
        assertParseSuccess(parser, NAME_DESC_BOB + CONTACT_DESC_AMY + CONTACT_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple addresses - last company accepted
        assertParseSuccess(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + COMPANY_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple job descriptions - last job descriptions accepted
        assertParseSuccess(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_AMY + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE_DESC_AMY
                + SALARY_DESC_BOB + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));


        // multiple tags - all accepted
        Role expectedRoleMultipleTags = new RoleBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB + JOBDESCRIPTION_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                new AddCommand(expectedRoleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Role expectedRole = new RoleBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY
                + CONTACT_DESC_AMY + EMAIL_DESC_AMY + COMPANY_DESC_AMY + WEBSITE_DESC_AMY
                + JOBDESCRIPTION_DESC_AMY + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_AMY, new AddCommand(expectedRole));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing contact prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_CONTACT_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + VALID_EMAIL_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing company prefix
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + VALID_COMPANY_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing job description prefix
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + VALID_JOBDESCRIPTION_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + VALID_SALARY_BOB + DEADLINE_DESC_BOB
                + VALID_EXPERIENCE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_CONTACT_BOB + VALID_EMAIL_BOB + VALID_COMPANY_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + VALID_SALARY_BOB
                + VALID_DEADLINE_BOB + VALID_EXPERIENCE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid contact
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_CONTACT_DESC + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Contact.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + INVALID_EMAIL_DESC + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + INVALID_COMPANY_DESC
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Company.MESSAGE_CONSTRAINTS);

        // invalid job description
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                        + INVALID_JOBDESCRIPTION_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY
                        + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB
                        + EXPERIENCE_DESC_BOB,
                JobDescription.MESSAGE_CONSTRAINTS);

        // invalid experience
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                        + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_AMY
                        + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB
                        + INVALID_EXPERIENCE_DESC,
                Experience.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                        + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_BOB
                        + INVALID_SALARY_DESC + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Salary.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_WEBSITE_DESC
                + SALARY_DESC_BOB + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Website.MESSAGE_CONSTRAINTS);

        // invalid deadline (does not exist)
        assertParseFailure(parser, NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                        + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE_DESC_BOB
                        + SALARY_DESC_BOB + INVALID_DEADLINE_DESC + EXPERIENCE_DESC_BOB,
                Deadline.DOES_NOT_EXIST);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + CONTACT_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_COMPANY_DESC + JOBDESCRIPTION_DESC_BOB + WEBSITE_DESC_AMY + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + CONTACT_DESC_BOB + EMAIL_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + COMPANY_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
