package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindContainsAnythingPredicate;

/**
 * Parses input arguments and creates a new FindAllCommand object
 */
public class FindAllCommandParser implements Parser<FindAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAllCommand
     * and returns a FindAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindAllCommand(new FindContainsAnythingPredicate(Arrays.asList(nameKeywords)));
    }

}
