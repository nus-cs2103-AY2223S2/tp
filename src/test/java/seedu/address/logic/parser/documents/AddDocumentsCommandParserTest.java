package seedu.address.logic.parser.documents;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COVER_LETTER_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COVER_LETTER_DESC_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COVER_LETTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESUME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RESUME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.RESUME_DESC_NETFLIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_GOOGLE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.AddDocumentsCommand;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;
import seedu.address.testutil.DocumentsBuilder;

public class AddDocumentsCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDocumentsCommand.MESSAGE_USAGE);
    private AddDocumentsCommandParser parser = new AddDocumentsCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, RESUME_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

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
        Documents expectedDocuments = new DocumentsBuilder(DOCUMENTS_GOOGLE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + targetIndex.getOneBased() + RESUME_DESC_GOOGLE
                + COVER_LETTER_DESC_GOOGLE, new AddDocumentsCommand(targetIndex, expectedDocuments));

        // multiple resume links - last resume link accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + RESUME_DESC_NETFLIX + RESUME_DESC_GOOGLE
                + COVER_LETTER_DESC_GOOGLE, new AddDocumentsCommand(targetIndex, expectedDocuments));

        // multiple cover letter links - last cover letter link accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + RESUME_DESC_GOOGLE + COVER_LETTER_DESC_NETFLIX
                + COVER_LETTER_DESC_GOOGLE, new AddDocumentsCommand(targetIndex, expectedDocuments));
    }
}
