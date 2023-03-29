package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.DeletePatientStatusCommand;

public class DeletePatientStatusCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientStatusCommand.MESSAGE_USAGE);

    private DeletePatientStatusCommandParser parser = new DeletePatientStatusCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no status index specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no person index and no status index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + " 1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + " -5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + " 1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + " 0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1" + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1" + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " 1";

        DeletePatientStatusCommand expectedCommand =
                new DeletePatientStatusCommand(targetIndex, Index.fromOneBased(1));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " 1" + " 1";

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
