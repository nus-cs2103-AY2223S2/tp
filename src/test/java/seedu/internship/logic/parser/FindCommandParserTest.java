package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
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
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FRONT;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.FindCommand;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Google"),
                        Arrays.asList("Software Developer"), Arrays.asList("applied"), Arrays.asList("2023-02-03"),
                        Arrays.asList("frontend")));

        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + TAG_DESC_FRONT, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + COMPANY_NAME_DESC_GOOGLE + " \n \t " + ROLE_DESC_GOOGLE + "\n "
                + STATUS_DESC_GOOGLE + "\n " + DATE_DESC_GOOGLE + "  \t" + TAG_DESC_FRONT + "   \n\t",
                expectedFindCommand);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        FindCommand expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Google", "Apple"),
                        Arrays.asList("Software Developer", "Mobile Developer"),
                        Arrays.asList("applied", "interview"), Arrays.asList("2023-02-01", "2023-02-03"),
                        Arrays.asList("backend", "frontend")));

        // whitespace only preamble (Note: List of strings in expectedFindCommand must be in this particular order.
        // This is because the parse methods in FindCommandParser use HashSet objects which automatically orders
        // everything. That's why assetParseSuccess still passes even though the sequence of fields may be different.
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_GOOGLE + COMPANY_NAME_DESC_APPLE
                + ROLE_DESC_GOOGLE + ROLE_DESC_APPLE + STATUS_DESC_GOOGLE + STATUS_DESC_APPLE + DATE_DESC_GOOGLE
                + DATE_DESC_APPLE + TAG_DESC_FRONT + TAG_DESC_BACK, expectedFindCommand);

        // multiple company names - all company names accepted
        // multiple roles - all roles accepted
        // multiple statuses - all statuses accepted
        // multiple dates - all dates accepted
        // multiple tags - all accepted
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + STATUS_DESC_APPLE + DATE_DESC_GOOGLE + DATE_DESC_APPLE
                + TAG_DESC_FRONT + TAG_DESC_BACK, expectedFindCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        FindCommand expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Collections.emptyList(),
                        Arrays.asList("Mobile Developer"), Arrays.asList("interview"), Arrays.asList("2023-02-01"),
                        Arrays.asList("frontend")));

        // no name
        assertParseSuccess(parser, ROLE_DESC_APPLE + STATUS_DESC_APPLE + DATE_DESC_APPLE + TAG_DESC_FRONT,
                expectedFindCommand);

        // no role
        expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Apple"), Collections.emptyList(),
                        Arrays.asList("interview"), Arrays.asList("2023-02-01"), Arrays.asList("frontend")));

        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + STATUS_DESC_APPLE + DATE_DESC_APPLE + TAG_DESC_FRONT,
                expectedFindCommand);

        // no status
        expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Apple"),
                        Arrays.asList("Mobile Developer"), Collections.emptyList(), Arrays.asList("2023-02-01"),
                        Arrays.asList("frontend")));

        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + DATE_DESC_APPLE + TAG_DESC_FRONT,
                expectedFindCommand);

        // no date
        expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Apple"),
                        Arrays.asList("Mobile Developer"), Arrays.asList("interview"), Collections.emptyList(),
                        Arrays.asList("frontend")));

        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + STATUS_DESC_APPLE + TAG_DESC_FRONT,
                expectedFindCommand);

        // zero tags
        expectedFindCommand =
                new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList("Apple"),
                        Arrays.asList("Mobile Developer"), Arrays.asList("interview"), Arrays.asList("2023-02-01"),
                        Collections.emptyList()));

        assertParseSuccess(parser, COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE + STATUS_DESC_APPLE + DATE_DESC_APPLE,
                expectedFindCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // no fields at all
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company name
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + INVALID_ROLE_DESC + ROLE_DESC_GOOGLE + DATE_DESC_GOOGLE
                + TAG_DESC_BACK + TAG_DESC_FRONT, Role.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + INVALID_STATUS_DESC
                + DATE_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, Status.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + INVALID_DATE_DESC + DATE_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE
                + INVALID_TAG_DESC + VALID_TAG_FRONT, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + INVALID_TAG_DESC, CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE
                        + STATUS_DESC_GOOGLE + DATE_DESC_GOOGLE + TAG_DESC_BACK + TAG_DESC_FRONT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
