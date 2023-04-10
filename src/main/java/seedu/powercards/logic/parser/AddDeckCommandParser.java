package seedu.powercards.logic.parser;

import seedu.powercards.logic.commands.deckcommands.AddDeckCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.deck.Deck;

/**
 * Parses input arguments and creates a new AddDeckCommand object
 */
public class AddDeckCommandParser implements Parser<AddDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCardCommand
     * and returns an AddCardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeckCommand parse(String args) throws ParseException {

        Deck deck = ParserUtil.parseDeck(args);

        return new AddDeckCommand(deck);
    }

}
