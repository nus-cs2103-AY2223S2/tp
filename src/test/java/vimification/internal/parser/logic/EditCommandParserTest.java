package vimification.internal.parser.logic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.EditCommand;
import vimification.internal.parser.ParserException;

public class EditCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final EditCommandParser INSTANCE = EditCommandParser.getInstance();

    @Test
    public void urecognizaleArgs_shouldThrow() {
        String input = "e \"description\" -t stickytag -p 0";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "e 10 -p 1p --before Thu";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));

        String input2 = "e 10 -l idola"; // must have 2 args for label
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input2));
    }

    @Test
    public void withArgs_shouldSuccess() {
        String input = "e 2 -l cry die -l now later -t 'get out of here' -s 0";
        EditCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "e 10 -d Mon -l haskell rust";
        EditCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "e 10 --deadline 2023-01-01 --title 'empty?'";
        EditCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 != null);
    }

    @Test
    public void noArg_shouldThrow() {
        String input = "e 1";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));
    }
}
