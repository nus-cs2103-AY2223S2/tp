package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.loyaltylift.logic.commands.FindCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.customer.CustomerNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCustomerCommand object
 */
public class FindCustomerCommandParser implements Parser<FindCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCustomerCommand
     * and returns a FindCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCustomerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCustomerCommand(new CustomerNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
