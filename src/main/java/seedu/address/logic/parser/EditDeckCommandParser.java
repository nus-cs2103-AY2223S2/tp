package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDeckCommand;
import seedu.address.logic.commands.SelectDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Deck;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            String i = String.valueOf(args.charAt(1));
            index = ParserUtil.parseIndex(i);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectDeckCommand.MESSAGE_USAGE), pe);
        }

        Deck editedDeck = ParserUtil.parseDeck(args.substring(2));

        return new EditDeckCommand(index, editedDeck);
    }
}
