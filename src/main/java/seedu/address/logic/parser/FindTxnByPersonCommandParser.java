package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTxnByPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.OnlyOnePersonPredicate;
import seedu.address.model.person.TxnContainsPersonPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindTxnByPersonCommandParser implements Parser<FindTxnByPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTxnByPersonCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTxnByPersonCommand.MESSAGE_USAGE));
        }

        return new FindTxnByPersonCommand(new TxnContainsPersonPredicate(trimmedArgs),
                new OnlyOnePersonPredicate(trimmedArgs));
    }

}
