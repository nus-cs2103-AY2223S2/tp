package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.NameCommand;
import seedu.address.logic.commands.exceptions.exceptions.ParseException;
import seedu.address.model.job.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new NameCommand object
 */
public class NameCommandParser implements Parser<NameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NameCommand
     * and returns a NameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new NameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
