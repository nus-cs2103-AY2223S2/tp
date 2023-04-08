package vimification.internal.parser.macro;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.macro.AddMacroCommand;
import vimification.internal.command.macro.DeleteMacroCommand;
import vimification.internal.command.macro.ListMacroCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.parser.ParserException;

public class MacroCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final MacroCommandParser INSTANCE = MacroCommandParser.getInstance();

    @Test
    public void unrecognizableArgs_shouldThrow() {
        String input = "macro cs2103t 'deadline is coming'";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "macro 1";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));

        String input2 = "macro 2023-01-01";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input2));
    }

    @Test
    public void withArgs_shouldSuccess() {
        String input = "macro -a cs2103 'a \"weekly quiz\" -d Fri -l cs2103 -p 1'";
        MacroCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd instanceof AddMacroCommand);

        String input1 = "macro -d cs2103";
        MacroCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 instanceof DeleteMacroCommand);

        String input2 = "macro -l";
        MacroCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 instanceof ListMacroCommand);
    }

    @Test
    public void tooManyArgs_shouldThrow() {
        String input = "macro -a touhou \"a 'play touhou'\" 'code touhou with C'";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "macro -d touhou 'voile the magic library'";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));

        String input2 = "macro -l kyoto";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input2));
    }

    @Test
    public void noArg_shouldThrow() {
        String input = "macro";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }

    @Test
    public void invalidArgs_shouldThrow() {
        String input = "macro -t 'Eastern Dream'";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }
}
