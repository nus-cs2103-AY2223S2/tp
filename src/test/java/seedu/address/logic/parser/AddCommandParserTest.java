package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRoles.AMY;
import static seedu.address.testutil.TypicalRoles.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.job.Address;
import seedu.address.model.job.Email;
import seedu.address.model.job.JobDescription;
import seedu.address.model.job.Name;
import seedu.address.model.job.Phone;
import seedu.address.model.job.Role;
import seedu.address.model.job.Experience;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.RoleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Role expectedRole = new RoleBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));

        // multiple job descriptions - last job descriptions accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_AMY + JOBDESCRIPTION_DESC_BOB + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB, new AddCommand(expectedRole));


        // multiple tags - all accepted
        Role expectedRoleMultipleTags = new RoleBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB + JOBDESCRIPTION_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                new AddCommand(expectedRoleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Role expectedRole = new RoleBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + WEBSITE
                + JOBDESCRIPTION_DESC_AMY + SALARY_DESC_AMY + DEADLINE_DESC_AMY + DEADLINE_DESC_AMY, new AddCommand(expectedRole));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing job description prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_JOBDESCRIPTION_BOB + WEBSITE + SALARY_DESC_AMY + DEADLINE_DESC_AMY
                + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + VALID_SALARY_AMY + DEADLINE_DESC_AMY
                + VALID_EXPERIENCE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + VALID_SALARY_AMY + VALID_EXPERIENCE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + JOBDESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid job description
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_JOBDESCRIPTION_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + WEBSITE
                        + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB
                        + EXPERIENCE_DESC_BOB,
                JobDescription.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + WEBSITE + SALARY_DESC_BOB
                        + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + JOBDESCRIPTION_DESC_BOB + WEBSITE + SALARY_DESC_BOB + DEADLINE_DESC_BOB
                + EXPERIENCE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + JOBDESCRIPTION_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SALARY_DESC_BOB
                + DEADLINE_DESC_BOB + EXPERIENCE_DESC_BOB
                , String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
