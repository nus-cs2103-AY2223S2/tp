package vimification.internal.parser.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.ui.SortCommand;
import vimification.internal.parser.ParserException;

public class SortCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final SortCommandParser INSTANCE = SortCommandParser.getInstance();

    @Test
    public void unrecognizableArgs_shouldThrow() {
        String input = "s sort";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }

    @Test
    public void oneArg_shouldSuccess() {
        String input = "s --deadline";
        SortCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "s -s";
        SortCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "s -p";
        SortCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 != null);
    }

    @Test
    public void noArg_shouldThrow() {
        String input = "s";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }

    @Test
    public void manyArgs_shouldThrow() {
        String input = "s -d -s";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "s --priority --status";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));
    }
}
