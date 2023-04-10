package seedu.powercards.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.deckcommands.EditDeckCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.deck.Deck;

public class EditDeckCommandParserTest {

    private final EditDeckCommandParser parser = new EditDeckCommandParser();

    @Test
    public void parse_validArgs_returnsEditDeckCommand() throws ParseException {
        Index index = Index.fromOneBased(1);
        Deck deck = new Deck("new deck name");
        EditDeckCommand expectedCommand = new EditDeckCommand(index, deck);
        assertEquals(expectedCommand, parser.parse("1 new deck name"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty arguments
        assertThrows(ParseException.class, () -> parser.parse(""));

        // missing index
        assertThrows(ParseException.class, () -> parser.parse("new deck name"));

        // invalid index
        assertThrows(ParseException.class, () -> parser.parse("0 new deck name"));
        assertThrows(ParseException.class, () -> parser.parse("-1 new deck name"));
        assertThrows(ParseException.class, () -> parser.parse("not a number new deck name"));

        // missing deck name
        assertThrows(ParseException.class, () -> parser.parse("1"));
    }

}
