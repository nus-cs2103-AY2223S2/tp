package vimification.internal.parser.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.ui.JumpCommand;
import vimification.internal.parser.ParserException;

public class JumpCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final JumpCommandParser INSTANCE = JumpCommandParser.getInstance();

    @Test
    public void noArg_shouldSuccess() {
        String input = "10";
        JumpCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);
    }

    @Test
    public void withArgs_shouldThrow() {
        String input = "10 ZUN";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "13 -l remilia";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));
    }
}
