package seedu.powercards.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.deckcommands.SelectDeckCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

public class SelectDeckCommandParserTest {
    private SelectDeckCommandParser parser = new SelectDeckCommandParser();

    @Test
    public void parse_validArgs_returnsSelectDeckCommand() throws ParseException {
        SelectDeckCommand expectedCommand = new SelectDeckCommand(INDEX_FIRST);
        assertEquals(expectedCommand, parser.parse("1"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 extra"));
    }
}
