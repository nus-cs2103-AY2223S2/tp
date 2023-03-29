package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.KEYDATE_DESC_OA;
import static seedu.address.logic.commands.CommandTestUtil.KEYDATE_DESC_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_MARCH_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_FEB_OA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalOpenings.GOOGLE;
import static seedu.address.testutil.TypicalOpenings.SHOPEE;
import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OpeningBuilder;
import seedu.ultron.logic.commands.AddCommand;
import seedu.ultron.logic.parser.AddCommandParser;
import seedu.ultron.model.opening.Status;
import seedu.ultron.model.opening.Email;
import seedu.ultron.model.opening.Position;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.Company;
import seedu.ultron.model.opening.Date;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Opening expectedOpening = new OpeningBuilder(SHOPEE).withDates(VALID_KEYDATE_FEB_OA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_OA, new AddCommand(expectedOpening));

        // multiple positions - last position accepted
        assertParseSuccess(parser, POSITION_DESC_GOOGLE + POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_OA, new AddCommand(expectedOpening));

        // multiple companys - last company accepted
        assertParseSuccess(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_GOOGLE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_OA, new AddCommand(expectedOpening));

        // multiple emails - last email accepted
        assertParseSuccess(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_GOOGLE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_OA, new AddCommand(expectedOpening));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE + STATUS_DESC_GOOGLE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_OA, new AddCommand(expectedOpening));

        // multiple dates - all accepted
        Opening expectedOpeningMultipleDates = new OpeningBuilder(SHOPEE).withDates(VALID_KEYDATE_FEB_OA, VALID_KEYDATE_MARCH_INTERVIEW)
                .build();
        assertParseSuccess(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE + STATUS_DESC_SHOPEE
                + REMARK_DESC_SHOPEE + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, new AddCommand(expectedOpeningMultipleDates));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero dates
        Opening expectedOpening = new OpeningBuilder(GOOGLE).withDates().build();
        assertParseSuccess(parser, POSITION_DESC_GOOGLE + COMPANY_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + STATUS_DESC_GOOGLE + REMARK_DESC_GOOGLE,
                new AddCommand(expectedOpening));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing position prefix
        assertParseFailure(parser, VALID_POSITION_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE,
                expectedMessage);

        // missing company prefix
        assertParseFailure(parser, POSITION_DESC_SHOPEE + VALID_COMPANY_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + VALID_EMAIL_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + VALID_STATUS_SHOPEE + VALID_REMARK_SHOPEE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_POSITION_SHOPEE + VALID_COMPANY_SHOPEE + VALID_EMAIL_SHOPEE
                + VALID_STATUS_SHOPEE + VALID_REMARK_SHOPEE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid position
        assertParseFailure(parser, INVALID_POSITION_DESC + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE
                + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, Position.MESSAGE_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, POSITION_DESC_SHOPEE + INVALID_COMPANY_DESC + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE
                + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, Company.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + INVALID_EMAIL_DESC
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE
                + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, Email.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + INVALID_STATUS_DESC + REMARK_DESC_SHOPEE
                + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, Status.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE
                + INVALID_DATE_DESC + VALID_KEYDATE_FEB_OA, Date.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_POSITION_DESC + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + INVALID_STATUS_DESC + REMARK_DESC_SHOPEE,
                Position.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + POSITION_DESC_SHOPEE + COMPANY_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + STATUS_DESC_SHOPEE + REMARK_DESC_SHOPEE + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
