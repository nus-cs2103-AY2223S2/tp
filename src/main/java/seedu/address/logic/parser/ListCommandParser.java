package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Objects;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_DESCRIPTION);

        if (Objects.equals(argMultimap.getPreamble(), "fishes")) {
            return ListCommand.LIST_FISHES;
        } else if (Objects.equals(argMultimap.getPreamble(), "tanks")) {
            return ListCommand.LIST_TANKS;
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
