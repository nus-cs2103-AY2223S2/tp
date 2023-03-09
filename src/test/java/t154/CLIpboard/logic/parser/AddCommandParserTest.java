package t154.CLIpboard.logic.parser;

import static t154.CLIpboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static t154.CLIpboard.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static t154.CLIpboard.logic.commands.CommandTestUtil.MODULE_DESC_CS2103;
import static t154.CLIpboard.logic.commands.CommandTestUtil.MODULE_DESC_CS2105;
import static t154.CLIpboard.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static t154.CLIpboard.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.TAG_DESC_TEAM1;
import static t154.CLIpboard.logic.commands.CommandTestUtil.TAG_DESC_TEAM2;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_MODULE_CS2103;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_MODULE_CS2105;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_TAG_TEAM1;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_TAG_TEAM2;
import static t154.CLIpboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static t154.CLIpboard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static t154.CLIpboard.testutil.TypicalStudents.AMY;
import static t154.CLIpboard.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import t154.CLIpboard.logic.commands.AddCommand;
import t154.CLIpboard.logic.commands.CommandTestUtil;
import t154.CLIpboard.model.student.Email;
import t154.CLIpboard.model.student.ModuleCode;
import t154.CLIpboard.model.student.Name;
import t154.CLIpboard.model.student.Phone;
import t154.CLIpboard.model.student.Student;
import t154.CLIpboard.model.student.StudentId;
import t154.CLIpboard.model.tag.Tag;
import t154.CLIpboard.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_TEAM1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                new AddCommand(expectedStudent));

        // multiple student ids - last student id accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_AMY
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                new AddCommand(expectedStudent));

        // multiple modules - all accepted
        Student expectedStudentMultipleModules = new StudentBuilder(BOB)
                .withModules(VALID_MODULE_CS2103, VALID_MODULE_CS2105).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2103 + MODULE_DESC_CS2105,
                new AddCommand(expectedStudentMultipleModules));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB)
                .withTags(VALID_TAG_TEAM1, VALID_TAG_TEAM2).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2103 + TAG_DESC_TEAM1 + TAG_DESC_TEAM2,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + STUDENTID_DESC_AMY
                + CommandTestUtil.MODULE_DESC_CS2105, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2103, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2103, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2103, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_STUDENTID_BOB
                + MODULE_DESC_CS2103, expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + VALID_MODULE_CS2103, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_STUDENTID_BOB
                + VALID_MODULE_CS2103, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2105 + TAG_DESC_TEAM2, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2105 + TAG_DESC_TEAM2, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2105 + TAG_DESC_TEAM2, Email.MESSAGE_CONSTRAINTS);

        // invalid studentId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_STUDENTID_DESC
                + MODULE_DESC_CS2105 + TAG_DESC_TEAM2, StudentId.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + INVALID_MODULE_DESC + TAG_DESC_TEAM2, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB
                + MODULE_DESC_CS2105 + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_STUDENTID_DESC
                + MODULE_DESC_CS2105, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTID_DESC_BOB + MODULE_DESC_CS2105 + TAG_DESC_TEAM1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
