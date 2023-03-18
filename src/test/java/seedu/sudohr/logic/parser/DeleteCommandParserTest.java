package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.sudohr.testutil.TypicalIds.ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.DeleteCommand;
import seedu.sudohr.model.employee.Id;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser,  " id/1", new DeleteCommand(ID_FIRST_PERSON));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, "1", expectedMessage);
        assertParseFailure(parser, "n/bobby a/nus", expectedMessage);
    }

    @Test
    public void parse_additionalFieldsProvided_failure() {
        assertParseFailure(parser, "id/1 n/bobby a/nus", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        assertParseFailure(parser, " id/a ", Id.MESSAGE_CONSTRAINTS);
    }
}
