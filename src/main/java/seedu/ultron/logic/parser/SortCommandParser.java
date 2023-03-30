package seedu.ultron.logic.parser;

import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ultron.logic.commands.SortCommand;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.opening.ContainsKeydatePredicate;
import seedu.ultron.model.opening.KeydateSort;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(new ContainsKeydatePredicate(), new KeydateSort(trimmedArgs));
    }

}
