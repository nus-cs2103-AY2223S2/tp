package vimification.internal.parser.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.ui.QuitCommand;
import vimification.internal.parser.ParserException;

public class QuitCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final QuitCommandParser INSTANCE = QuitCommandParser.getInstance();

    @Test
    public void noArg_shouldSuccess() {
        String input = "quit";
        QuitCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "q!"; // short command
        QuitCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }

    @Test
    public void withArgs_shouldThrow() {
        String input = "quit 1";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "quit -a";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));
    }
}
