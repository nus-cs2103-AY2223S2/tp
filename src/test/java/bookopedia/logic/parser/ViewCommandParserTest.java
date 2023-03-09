package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookopedia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import bookopedia.logic.commands.ViewCommand;

public class ViewCommandParserTest {
    private ViewCommandParser testParser = new ViewCommandParser();
    @Test
    public void parse_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testParser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(testParser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

}
