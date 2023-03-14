package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANTS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NO_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalListings.SOFTWARE_DEVELOPER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Listing expectedListing =
                new ListingBuilder(SOFTWARE_DEVELOPER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TITLE_DESC
                + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC, new AddCommand(expectedListing));

        // multiple title - last title accepted
        assertParseSuccess(parser, " t/other_title" + VALID_TITLE_DESC
                + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC, new AddCommand(expectedListing));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, VALID_TITLE_DESC + " d/other_description"
                + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC, new AddCommand(expectedListing));

        // multiple tags - all accepted
        assertParseSuccess(parser, VALID_TITLE_DESC
                + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC, new AddCommand(expectedListing));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Listing expectedListing = new ListingBuilder(SOFTWARE_DEVELOPER).withApplicants(VALID_NO_APPLICANTS).build();
        assertParseSuccess(parser, VALID_TITLE_DESC + VALID_DESCRIPTION_DESC,
                new AddCommand(expectedListing));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, VALID_TITLE_DESC + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC,
                expectedMessage);

        // missing applicant prefix
        assertParseFailure(parser, VALID_TITLE_DESC + VALID_DESCRIPTION_DESC + VALID_APPLICANT_NAME_BENEDICT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE + VALID_DESCRIPTION + VALID_APPLICANT_NAME_BENEDICT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + VALID_DESCRIPTION_DESC
                + VALID_APPLICANTS_DESC, JobTitle.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, VALID_TITLE_DESC + INVALID_DESCRIPTION_DESC
                + VALID_APPLICANTS_DESC, JobDescription.MESSAGE_CONSTRAINTS);

        // invalid applicant
        assertParseFailure(parser, VALID_TITLE_DESC + VALID_DESCRIPTION_DESC
                + INVALID_APPLICANT_DESC, Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC,
                JobTitle.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_TITLE_DESC
                        + VALID_DESCRIPTION_DESC + VALID_APPLICANTS_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
