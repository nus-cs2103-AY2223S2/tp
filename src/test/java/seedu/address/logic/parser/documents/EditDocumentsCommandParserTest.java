package seedu.address.logic.parser.documents;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COVER_LETTER_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COVER_LETTER_DESC_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COVER_LETTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESUME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RESUME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.RESUME_DESC_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_NETFLIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.model.documents.ResumeLink;
import seedu.address.testutil.EditDocumentsDescriptorBuilder;

public class EditDocumentsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDocumentsCommand.MESSAGE_USAGE);

    private EditDocumentsCommandParser parser = new EditDocumentsCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, RESUME_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditDocumentsCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + RESUME_DESC_GOOGLE + COVER_LETTER_DESC_GOOGLE,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + RESUME_DESC_GOOGLE + COVER_LETTER_DESC_GOOGLE,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_RESUME_DESC + INVALID_COVER_LETTER_DESC,
                ResumeLink.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FOURTH_APPLICATION;
        String userInput = targetIndex.getOneBased() + RESUME_DESC_GOOGLE + COVER_LETTER_DESC_GOOGLE;

        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_GOOGLE).withCoverLetterLink(VALID_COVER_LETTER_LINK_GOOGLE).build();
        EditDocumentsCommand expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIFTH_APPLICATION;
        String userInput = targetIndex.getOneBased() + RESUME_DESC_GOOGLE;

        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_GOOGLE).build();
        EditDocumentsCommand expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // resume link
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + RESUME_DESC_GOOGLE;
        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_GOOGLE).build();
        EditDocumentsCommand expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // cover letter link
        userInput = targetIndex.getOneBased() + COVER_LETTER_DESC_GOOGLE;
        descriptor = new EditDocumentsDescriptorBuilder().withCoverLetterLink(VALID_COVER_LETTER_LINK_GOOGLE).build();
        expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIFTH_APPLICATION;
        String userInput = targetIndex.getOneBased() + RESUME_DESC_GOOGLE + COVER_LETTER_DESC_GOOGLE
                + RESUME_DESC_NETFLIX + COVER_LETTER_DESC_NETFLIX;

        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_NETFLIX).withCoverLetterLink(VALID_COVER_LETTER_LINK_NETFLIX).build();
        EditDocumentsCommand expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIFTH_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_RESUME_DESC + RESUME_DESC_GOOGLE;
        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_GOOGLE).build();
        EditDocumentsCommand expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COVER_LETTER_DESC_GOOGLE + INVALID_RESUME_DESC
                + RESUME_DESC_GOOGLE;
        descriptor = new EditDocumentsDescriptorBuilder().withResumeLink(VALID_RESUME_LINK_GOOGLE)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_GOOGLE).build();
        expectedCommand = new EditDocumentsCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
