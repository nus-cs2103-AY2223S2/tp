package seedu.internship.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.internship.logic.commands.DeleteAllCommand;

import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class DeleteAllCommandParserTest {

    private DeleteAllCommandParser parser = new DeleteAllCommandParser();

    @Test
    public void parse_correctCode_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DeleteAllCommand.CONFIRMATION_CODE,
                new DeleteAllCommand(DeleteAllCommand.CONFIRMATION_CODE));
    }

    @Test
    public void parse_incorrectCode_success() {
        //parse still successful if incorrect code
        String incorrectCode = "please";
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + incorrectCode, new DeleteAllCommand(incorrectCode));
    }
}
