package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindLeadStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindContainsStatusPredicate;

/**
 * Parses input arguments and creates a new FindLeadStatusCommand object
 */
public class FindLeadStatusCommandParser implements Parser<FindLeadStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindLeadStatusCommand
     * and returns a FindLeadStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindLeadStatusCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLeadStatusCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindLeadStatusCommand(new FindContainsStatusPredicate(Arrays.asList(nameKeywords)));
    }

}
