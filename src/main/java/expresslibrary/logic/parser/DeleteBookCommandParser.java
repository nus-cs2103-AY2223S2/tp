package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_FORCE;
import static java.util.Objects.requireNonNull;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.DeleteBookCommand;
import expresslibrary.logic.commands.DeletePersonCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBookCommand object
 */
public class DeleteBookCommandParser implements Parser<DeleteBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteBookCommand
     * and returns a DeleteBookCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FORCE);

        Index bookIndex;
        try {
            bookIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookCommand.MESSAGE_USAGE), pe);
        }

        Boolean deleteOption = false;
        if (argMultimap.getValue(PREFIX_FORCE).isPresent()) {
            deleteOption = true;
        }

        return new DeleteBookCommand(bookIndex, deleteOption);
    }

}
