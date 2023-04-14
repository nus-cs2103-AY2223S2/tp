package seedu.powercards.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.deckcommands.SelectDeckCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectDeck object
 */
public class SelectDeckCommandParser implements Parser<SelectDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectDeckCommand
     * and returns an SelectDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectDeckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectDeckCommand.MESSAGE_USAGE), pe);
        }

        return new SelectDeckCommand(index);
    }
}
