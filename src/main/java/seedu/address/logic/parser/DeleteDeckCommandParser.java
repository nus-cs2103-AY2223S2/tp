package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDeck object
 */
public class DeleteDeckCommandParser implements Parser<DeleteDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDeckCommand
     * and returns an DeleteDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDeckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDeckCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteDeckCommand(index);
    }
}
