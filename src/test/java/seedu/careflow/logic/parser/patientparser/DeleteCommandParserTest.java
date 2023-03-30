package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.careflow.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.careflow.model.patient.Ic.MESSAGE_CONSTRAINTS;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.DeleteCommand;
import seedu.careflow.model.patient.Ic;


class DeleteCommandParserTest {

    private final DeleteCommandParser deleteCommandParser = new DeleteCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(deleteCommandParser, " -i 1", new DeleteCommand(INDEX_FIRST));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -i -1", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidNonNumericalIndex_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -i a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_validIc_returnsDeleteCommand() {
        Ic ic = new Ic("A7654321B");
        assertParseSuccess(deleteCommandParser, " -ic A7654321B", new DeleteCommand(ic));
    }

    @Test
    public void parse_invalidNumberAsIc_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -ic 123", MESSAGE_CONSTRAINTS);
    }

    @Test public void parse_invalidLengthIc_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -ic A7654321BB", MESSAGE_CONSTRAINTS);
    }
}
