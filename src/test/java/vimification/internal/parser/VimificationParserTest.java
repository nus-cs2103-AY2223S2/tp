package vimification.internal.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.Command;

public class VimificationParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;

    private static final VimificationParser PARSER = VimificationParser.getInstance(null);

    @Test
    public void unknownCommand_shouldThrow() {
        String input = ". sleep";
        ParserException ex = assertThrows(EXPECTED_EXCEPTION_CLASS, () -> PARSER.parse(input));
        assertEquals("Unknown command", ex.getMessage());

        String input1 = "_ hello";
        ex = assertThrows(EXPECTED_EXCEPTION_CLASS, () -> PARSER.parse(input1));
        assertEquals("Unknown command", ex.getMessage());
    }

    @Test
    public void validCommand_shouldSuccess() {
        String input = "a 'play touhou 6'";
        Command cmd = PARSER.parse(input);
        assertTrue(cmd != null);

        String input1 = "a \"todo 1\"";
        Command cmd1 = PARSER.parse(input1);
        assertTrue(cmd1 != null);
    }
}
