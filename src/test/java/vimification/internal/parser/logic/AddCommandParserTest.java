package vimification.internal.parser.logic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.AddCommand;
import vimification.internal.parser.ParserException;

public class AddCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final AddCommandParser INSTANCE = AddCommandParser.getInstance();

    @Test
    public void unrecognizableArgs_shouldThrow() {
        String input = "a deadline \"description\"-t stickytag -p 0";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }

    @Test
    public void noOptionalArg_shouldSuccess() {
        String input = "a sleep";
        AddCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "a 'play Touhou 7'";
        AddCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "a \"play Touhou 8\"";
        AddCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 != null);
    }

    @Test
    public void withOptionalArgs_shouldSuccess() {
        String input = "a suffering -p 3 -l \"cry\" -l \"now\"";
        AddCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "a 'learn \"HASKELL\"' -p 0 -l haskell --label proglang";
        AddCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }
}
