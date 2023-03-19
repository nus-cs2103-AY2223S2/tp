package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DESC_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DESC_ALT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LISTING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_LISTING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.testutil.EditListingDescriptorBuilder;

public class EditCommandParserTest {

    private static final String APPLICANT_EMPTY = " " + PREFIX_APPLICANT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC,
                JobTitle.MESSAGE_CONSTRAINTS); // invalid job title
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                JobDescription.MESSAGE_CONSTRAINTS); // invalid job description
        assertParseFailure(parser, "1" + INVALID_APPLICANT_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid applicant

        // invalid job description followed by valid job title
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + VALID_TITLE_DESC,
                JobDescription.MESSAGE_CONSTRAINTS);

        // valid job title followed by invalid job description. The test case for invalid job description followed
        // by valid job title is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + VALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                JobDescription.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_APPLICANT} alone will reset the applicants of the {@code Listing} being edited,
        // parsing it together with a valid applicant results in error
        assertParseFailure(parser, "1" + VALID_APPLICANT_NAME_BENEDICT_DESC + VALID_APPLICANT_NAME_CHRIS_DESC
                + APPLICANT_EMPTY, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + VALID_APPLICANT_NAME_BENEDICT_DESC + APPLICANT_EMPTY
                + VALID_APPLICANT_NAME_CHRIS_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + APPLICANT_EMPTY + VALID_APPLICANT_NAME_BENEDICT_DESC
                + VALID_APPLICANT_NAME_CHRIS_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                JobTitle.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_LISTING;
        String userInput = targetIndex.getOneBased() + VALID_DESCRIPTION_DESC + VALID_APPLICANT_NAME_CHRIS_DESC
                + VALID_TITLE_DESC + VALID_APPLICANT_NAME_BENEDICT_DESC;

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE)
                .withJobDescription(VALID_DESCRIPTION)
                .withApplicants(VALID_APPLICANT_NAME_CHRIS, VALID_APPLICANT_NAME_BENEDICT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_LISTING;
        String userInput = targetIndex.getOneBased() + VALID_TITLE_DESC + VALID_DESCRIPTION_DESC;

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_DESCRIPTION)
                .withJobTitle(VALID_TITLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // job title
        Index targetIndex = INDEX_THIRD_LISTING;
        String userInput = targetIndex.getOneBased() + VALID_TITLE_DESC;
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // job description
        userInput = targetIndex.getOneBased() + VALID_DESCRIPTION_DESC;
        descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // applicants
        userInput = targetIndex.getOneBased() + VALID_APPLICANT_NAME_BENEDICT_DESC;
        descriptor = new EditListingDescriptorBuilder().withApplicants(VALID_APPLICANT_NAME_BENEDICT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_LISTING;
        String userInput = targetIndex.getOneBased() + VALID_TITLE_DESC + VALID_DESCRIPTION_DESC
                + VALID_TITLE_DESC + VALID_DESCRIPTION_DESC + VALID_APPLICANT_NAME_BENEDICT_DESC
                + VALID_TITLE_DESC_ALT + VALID_DESCRIPTION_DESC_ALT + VALID_APPLICANT_NAME_CHRIS_DESC;

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_DESCRIPTION_ALT)
                .withJobTitle(VALID_TITLE_ALT).withApplicants(VALID_APPLICANT_NAME_BENEDICT,
                        VALID_APPLICANT_NAME_CHRIS)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_LISTING;
        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + VALID_DESCRIPTION_DESC;
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder()
                .withJobDescription(VALID_DESCRIPTION).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VALID_TITLE_DESC + INVALID_DESCRIPTION_DESC + VALID_DESCRIPTION_DESC;
        descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_DESCRIPTION)
                .withJobTitle(VALID_TITLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetApplicants_success() {
        Index targetIndex = INDEX_SECOND_LISTING;
        String userInput = targetIndex.getOneBased() + APPLICANT_EMPTY;

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withApplicants().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
