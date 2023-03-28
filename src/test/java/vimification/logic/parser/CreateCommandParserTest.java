package vimification.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.logic.AddCommand;
import vimification.internal.parser.CreateCommandParser;
import vimification.internal.parser.ParserException;

public class CreateCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;

    @Test
    public void unrecognizableKeyword_shouldFail() {
        String input = "itodoTrue";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input));
    }

    @Test
    public void unrecognizableType_shouldFail() {
        String input1 = "i todogood bye";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input1));
        String input2 = "i deadlinehomework is suffering";
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> CreateCommandParser.getInstance().parse(input2));
    }

    @Test
    public void validCommand_shouldSuccess() {
        String input1 = "i todo play Touhou 7";
        AddCommand cmd1 = CreateCommandParser.getInstance().parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "i deadline to play Touhou 8 /<datetime>";
        AddCommand cmd2 = CreateCommandParser.getInstance().parse(input2);
        assertTrue(cmd2 != null);
    }
}
