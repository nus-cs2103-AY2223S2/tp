package fasttrack.logic.parser.delete;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.delete.DeleteCategoryCommand;

class DeleteCategoryCommandParserTest {
    private final DeleteCategoryCommandParser parser = new DeleteCategoryCommandParser();

    @Test
    void parse_validArgs_returnsDeleteCategoryCommand() {
        String input1 = "1";
        DeleteCategoryCommand expected1 = new DeleteCategoryCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, input1, expected1);
    }

    @Test
    void parse_invalidValue_throwsParseException() {
        // Invalid index
        String input1 = "0";
        assertParseFailure(parser, input1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCategoryCommand.MESSAGE_USAGE));

        // Missing index
        String input2 = "";
        assertParseFailure(parser, input2, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCategoryCommand.MESSAGE_USAGE));
    }
}
