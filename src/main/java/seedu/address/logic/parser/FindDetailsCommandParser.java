package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindDetailsCommand object
 */
public class FindDetailsCommandParser implements Parser<FindDetailsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindDetailsCommand
     * and returns a FindDetailsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDetailsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDetailsCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDetailsCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
