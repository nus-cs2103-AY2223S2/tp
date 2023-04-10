package seedu.powercards.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.deckcommands.EditDeckCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.deck.Deck;

/**
 * Parses input arguments and creates a new EditDeckCommand object
 */
public class EditDeckCommandParser implements Parser<EditDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDeckCommand
     * and returns an EditDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDeckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] indexAndDeckName = trimmedArgs.split(" ", 2);

        if (indexAndDeckName.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDeckCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(indexAndDeckName[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDeckCommand.MESSAGE_USAGE), pe);
        }

        Deck editedDeck = ParserUtil.parseDeck(indexAndDeckName[1]);

        return new EditDeckCommand(index, editedDeck);
    }


}
