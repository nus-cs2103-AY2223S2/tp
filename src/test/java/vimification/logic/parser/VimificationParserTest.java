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

    @Test
    public void unknownCommand_shouldThrow() {
        String input = ". todo sleep";
        ParserException ex = assertThrows(
                EXPECTED_EXCEPTION_CLASS,
                () -> VimificationParser.getInstance().parse(input));
        assertEquals("Unknown command", ex.getMessage());
    }

    @Test
    public void validCommand_shouldSuccess() {
        String input = "i todo play touhou 6";
        LogicCommand cmd = VimificationParser.getInstance().parse(input);
        assertTrue(cmd != null);
    }
}
