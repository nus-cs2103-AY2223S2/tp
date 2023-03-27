package vimification.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.CreateCommand;
import vimification.internal.parser.CreateCommandParser;
import vimification.internal.parser.ParserException;

public class CreateCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;

    @Test
    public void unrecognizableKeyword_shouldFail() {
        String input = "itodoTrue";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input));
    }

    @Test
    public void unrecognizableType_shouldFail() {
        String input1 = "i todogood bye";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input1));
        String input2 = "i deadlinehomework is suffering";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input2));
    }

    @Test
    public void urecognizaleArguments_shouldThrow() {
        String input = "i deadline \"description\"-t stickytag -p 0";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input));
    }

    @Test
    public void validCommand_shouldSuccess() {
        String input = "i todo sleep";
        CreateCommand cmd = CreateCommandParser.getInstance().parse(input);
        assertTrue(cmd != null);

        String input1 = "i todo 'play Touhou 7'";
        CreateCommand cmd1 = CreateCommandParser.getInstance().parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "i deadline 'to play Touhou 8' --by <datetime>";
        CreateCommand cmd2 = CreateCommandParser.getInstance().parse(input2);
        assertTrue(cmd2 != null);
    }

    @Test
    public void optionalArgs_shouldSuccess() {
        String input = "i todo suffering -p 3 -t \"every day\" -t \"now\"";
        CreateCommand cmd = CreateCommandParser.getInstance().parse(input);
        assertTrue(cmd != null);

        String input1 = "i todo 'learn \"HASKELL\"' -p 0 -t haskell --tag proglang";
        CreateCommand cmd1 = CreateCommandParser.getInstance().parse(input1);
        assertTrue(cmd1 != null);
    }
}
