package seedu.address.logic.parser;


import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePatientCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeletePatientCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeletePatientCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePatientCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format("%s\n%s", ParserUtil.MESSAGE_INVALID_INDEX,
            DeletePatientCommand.getCommandUsage());
    private DeletePatientCommandParser parser = new DeletePatientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteDoctorCommand() {
        assertParseSuccess(parser, "1", new DeletePatientCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }
}
