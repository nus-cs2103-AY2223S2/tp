package vimification.internal.parser.logic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.DeleteCommand;
import vimification.internal.parser.ParserException;

public class DeleteCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final DeleteCommandParser INSTANCE = DeleteCommandParser.getInstance();

    @Test
    public void unrecognizableArgs_shouldThrow() {
        String input = "d 1 \"description\"-t stickytag -p 0";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }

    @Test
    public void noOptionalArg_shouldSuccess() {
        String input = "d 1";
        DeleteCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "d 100";
        DeleteCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }

    @Test
    public void withOptionalArgs_shouldSuccess() {
        String input = "d 2 -l \"cry\" -l \"now\"";
        DeleteCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "d 10 -d -l haskell --label proglang";
        DeleteCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "d 10 -d";
        DeleteCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 != null);
    }

    @Test
    public void invalidArgs_shouldThrow() {
        String input = "d 1 -t";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }
}
