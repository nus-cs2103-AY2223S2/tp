package expresslibrary.logic.parser;

import static expresslibrary.logic.parser.CliSyntax.PREFIX_FORCE;
import static java.util.Objects.requireNonNull;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.DeletePersonCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeletePersonCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FORCE);

        Index personIndex;
        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, pe);
        }

        Boolean deleteOption = false;
        if (argMultimap.getValue(PREFIX_FORCE).isPresent()) {
            deleteOption = true;
        }

        return new DeletePersonCommand(personIndex, deleteOption);
    }

}
