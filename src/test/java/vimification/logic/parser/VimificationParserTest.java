package vimification.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.LogicCommand;
import vimification.internal.parser.ParserException;
import vimification.internal.parser.VimificationParser;

public class VimificationParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;

    private static final VimificationParser PARSER = VimificationParser.getInstance(null);

    @Test
    public void unknownCommand_shouldThrow() {
        String input = ". todo sleep";
        ParserException ex = assertThrows(
                EXPECTED_EXCEPTION_CLASS,
                () -> PARSER.parse(input));
        assertEquals("Unknown command", ex.getMessage());
        String input1 = "i hello world";
        ex = assertThrows(
                EXPECTED_EXCEPTION_CLASS,
                () -> PARSER.parse(input1));
        assertEquals("Unable to parse input: " + input1, ex.getMessage());
    }

    @Test
    public void validCommand_shouldSuccess() {
        String input = "i todo play touhou 6";
        LogicCommand cmd = PARSER.parse(input);
        assertTrue(cmd != null);
    }
}
