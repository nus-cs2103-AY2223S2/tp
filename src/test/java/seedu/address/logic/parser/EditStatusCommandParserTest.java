package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_PENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_PENDING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.model.application.InternshipStatus;

public class EditStatusCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStatusCommand.MESSAGE_USAGE);
    private EditStatusCommandParser parser = new EditStatusCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STATUS_PENDING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_STATUS_PENDING,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_STATUS_PENDING,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC,
                InternshipStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + targetIndex.getOneBased() + STATUS_DESC_PENDING,
                new EditStatusCommand(targetIndex, InternshipStatus.PENDING));

        // multiple statuses - last phone accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + STATUS_DESC_ACCEPTED + STATUS_DESC_PENDING,
                new EditStatusCommand(targetIndex, InternshipStatus.PENDING));
    }
}
