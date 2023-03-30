package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC_3;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC_4;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC_5;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_FEB_OA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_MARCH_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.KEYDATE_DESC_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.KEYDATE_DESC_OA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexesNew.INDEX_FIRST_OPENING;
import static seedu.address.testutil.TypicalIndexesNew.INDEX_SECOND_OPENING;
import static seedu.address.testutil.TypicalIndexesNew.INDEX_THIRD_OPENING;
import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditOpeningDescriptorBuilder;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.EditCommand;
import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;
import seedu.ultron.logic.parser.EditCommandParser;
import seedu.ultron.model.opening.Status;
import seedu.ultron.model.opening.Email;
import seedu.ultron.model.opening.Position;
import seedu.ultron.model.opening.Company;
import seedu.ultron.model.opening.Date;

public class EditCommandParserTest {

    private static final String DATE_EMPTY = " " + PREFIX_KEYDATE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_POSITION_GOOGLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + POSITION_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + POSITION_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC_1, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC_2, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_DATE_DESC_1, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC_2, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC_3, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC_4, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC_5, Date.MESSAGE_CONSTRAINTS); // invalid date
        // invalid company followed by valid email
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC_1 + EMAIL_DESC_GOOGLE, Company.MESSAGE_CONSTRAINTS);

        // valid company followed by invalid company. The test case for invalid company followed by valid company
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + COMPANY_DESC_SHOPEE + INVALID_COMPANY_DESC_2, Company.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_DATE} alone will reset the dates of the {@code Opening} being edited,
        // parsing it together with a valid date results in error
        assertParseFailure(parser, "1" + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA + DATE_EMPTY, Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + KEYDATE_DESC_INTERVIEW + DATE_EMPTY + KEYDATE_DESC_OA, Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DATE_EMPTY + KEYDATE_DESC_INTERVIEW + KEYDATE_DESC_OA, Date.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC + INVALID_EMAIL_DESC + VALID_STATUS_GOOGLE + VALID_COMPANY_GOOGLE,
                Position.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_OPENING;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_SHOPEE + KEYDATE_DESC_INTERVIEW
                + EMAIL_DESC_GOOGLE + STATUS_DESC_GOOGLE + POSITION_DESC_GOOGLE + KEYDATE_DESC_OA;

        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withPosition(VALID_POSITION_GOOGLE)
                .withCompany(VALID_COMPANY_SHOPEE).withEmail(VALID_EMAIL_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
                .withDates(VALID_KEYDATE_MARCH_INTERVIEW, VALID_KEYDATE_FEB_OA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_OPENING;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_SHOPEE + EMAIL_DESC_GOOGLE;

        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withCompany(VALID_COMPANY_SHOPEE)
                .withEmail(VALID_EMAIL_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // position
        Index targetIndex = INDEX_THIRD_OPENING;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_GOOGLE;
        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withPosition(VALID_POSITION_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = targetIndex.getOneBased() + COMPANY_DESC_GOOGLE;
        descriptor = new EditOpeningDescriptorBuilder().withCompany(VALID_COMPANY_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_GOOGLE;
        descriptor = new EditOpeningDescriptorBuilder().withEmail(VALID_EMAIL_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_GOOGLE;
        descriptor = new EditOpeningDescriptorBuilder().withStatus(VALID_STATUS_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // dates
        userInput = targetIndex.getOneBased() + KEYDATE_DESC_OA;
        descriptor = new EditOpeningDescriptorBuilder().withDates(VALID_KEYDATE_MARCH_INTERVIEW).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_OPENING;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_GOOGLE + STATUS_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + KEYDATE_DESC_INTERVIEW + COMPANY_DESC_GOOGLE + STATUS_DESC_GOOGLE + EMAIL_DESC_GOOGLE + KEYDATE_DESC_OA
                + COMPANY_DESC_SHOPEE + STATUS_DESC_SHOPEE + EMAIL_DESC_SHOPEE + KEYDATE_DESC_INTERVIEW;

        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withCompany(VALID_COMPANY_SHOPEE)
                .withEmail(VALID_EMAIL_SHOPEE).withStatus(VALID_STATUS_SHOPEE).withDates(VALID_KEYDATE_FEB_OA, VALID_KEYDATE_MARCH_INTERVIEW)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_OPENING;
        String userInput = targetIndex.getOneBased() + INVALID_COMPANY_DESC_1 + COMPANY_DESC_SHOPEE;
        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withCompany(VALID_COMPANY_SHOPEE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_SHOPEE + INVALID_COMPANY_DESC_2 + STATUS_DESC_SHOPEE
                + COMPANY_DESC_SHOPEE;
        descriptor = new EditOpeningDescriptorBuilder().withCompany(VALID_COMPANY_SHOPEE).withEmail(VALID_EMAIL_SHOPEE)
                .withStatus(VALID_STATUS_SHOPEE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetDates_success() {
        Index targetIndex = INDEX_THIRD_OPENING;
        String userInput = targetIndex.getOneBased() + DATE_EMPTY;

        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder().withDates().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
