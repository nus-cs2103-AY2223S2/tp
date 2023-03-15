package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.POSITION_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.POSITION_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FUN;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_TAG_DESC;


import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalInternships.SE1;
import static seedu.internship.testutil.TypicalInternships.ML1;
import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CommandTestUtil;
import seedu.internship.model.internship.*;
import seedu.internship.model.tag.Tag;
import seedu.internship.testutil.InternshipBuilder;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(SE1).withTags(VALID_TAG_FUN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1, new AddCommand(expectedInternship));

        // multiple positions - last position accepted
        assertParseSuccess(parser, POSITION_DESC_ML1 + POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1, new AddCommand(expectedInternship));

        // multiple companies - last company accepted
        assertParseSuccess(parser, POSITION_DESC_SE1 + COMPANY_DESC_ML1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1, new AddCommand(expectedInternship));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_ML1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1, new AddCommand(expectedInternship));

        // multiple descriptions - last address accepted
        assertParseSuccess(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_ML1 + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(SE1).withTags(VALID_TAG_FUN, VALID_TAG_IMPORTANT)
                .build();
        assertParseSuccess(parser,POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1
                + DESCRIPTION_DESC_SE1 + TAG_DESC_SE1 + TAG_DESC_ML1 , new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(ML1).withTags().build();
        assertParseSuccess(parser, POSITION_DESC_ML1 + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + DESCRIPTION_DESC_ML1,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing position prefix
        assertParseFailure(parser, VALID_POSITION_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1,
                expectedMessage);

        // missing company prefix
        assertParseFailure(parser, POSITION_DESC_SE1 + VALID_COMPANY_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + VALID_STATUS_SE1 + DESCRIPTION_DESC_SE1,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + VALID_DESCRIPTION_SE1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_POSITION_SE1 + VALID_COMPANY_SE1 + VALID_STATUS_SE1 + VALID_DESCRIPTION_SE1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid position
        assertParseFailure(parser, INVALID_POSITION_DESC + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1
                + TAG_DESC_ML1 + TAG_DESC_SE1, Position.MESSAGE_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, POSITION_DESC_SE1 + INVALID_COMPANY_DESC + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1
                + TAG_DESC_ML1 + TAG_DESC_SE1, Company.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + INVALID_STATUS_DESC + DESCRIPTION_DESC_SE1
                + TAG_DESC_ML1 + TAG_DESC_SE1, Status.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + INVALID_DESCRIPTION_DESC
                + TAG_DESC_ML1 + TAG_DESC_SE1, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1
                + INVALID_TAG_DESC + TAG_DESC_SE1, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_POSITION_DESC + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + INVALID_DESCRIPTION_DESC,
                Position.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + POSITION_DESC_SE1 + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1
                        + TAG_DESC_ML1 + TAG_DESC_SE1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
