package vimification.internal.parser.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.internal.command.ui.FilterCommand;
import vimification.internal.parser.ParserException;

public class FilterCommandParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;
    private static final FilterCommandParser INSTANCE = FilterCommandParser.getInstance();

    @Test
    public void defaultMode_oneArg_shouldSuccess() {
        String input = "f --keyword 'touhou'";
        FilterCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "f --label touhou";
        FilterCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);

        String input2 = "f --before Fri";
        FilterCommand cmd2 = INSTANCE.parse(input2);
        assertTrue(cmd2 != null);

        String input3 = "f --after 2023-01-01 00:00";
        FilterCommand cmd3 = INSTANCE.parse(input3);
        assertTrue(cmd3 != null);
    }

    @Test
    public void defaultMode_manyArgs_shouldThrow() {
        String input = "f --status 1 --p 0";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "f --keyword touhou -l touhou";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));
    }

    @Test
    public void andMode_shouldSuccess() {
        String input = "f -a -w touhou -l touhou -p 0";
        FilterCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "f --and -w voile";
        FilterCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }

    @Test
    public void orMode_shouldSuccess() {
        String input = "f -o -w touhou";
        FilterCommand cmd = INSTANCE.parse(input);
        assertTrue(cmd != null);

        String input1 = "f --or --label touhou -p 0";
        FilterCommand cmd1 = INSTANCE.parse(input1);
        assertTrue(cmd1 != null);
    }

    @Test
    public void invalidArgs_shouldThrow() {
        String input = "f -t monogatari"; // no -t flag for filter, use -w
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "f --default"; // implicitly default
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));

        String input2 = "f -w yukari -d Mon"; // no --deadline flag, use --before or --after
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input2));
    }

    @Test
    public void unrecognizableArgs_shouldThrow() {
        String input = "f filter";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input));

        String input1 = "f 10";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input1));

        String input2 = "f 2023-01-01";
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> INSTANCE.parse(input2));
    }
}
