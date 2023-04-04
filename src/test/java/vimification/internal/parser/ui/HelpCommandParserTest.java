package vimification.internal.parser.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.ui.HelpCommand;
import vimification.internal.parser.ParserException;

public class HelpCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final HelpCommandParser INSTANCE = HelpCommandParser.getInstance();

    @Test
    public void noArg_shouldSuccess() {
        String input = "help";
        HelpCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "help    "; // trailling whitespacess
        HelpCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }

    @Test
    public void withArgs_shouldThrow() {
        String input = "help 1";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "help -a";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));
    }
}
