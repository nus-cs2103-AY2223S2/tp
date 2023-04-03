package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.parent.ParentFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.parent.ParentNameContainsKeywordsPredicate;

/**
 * a ParentFindCommandParser to parse the parent find command
 */
public class ParentFindCommandParser implements Parser<ParentFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StudentFindCommand
     * and returns a StudentFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ParentFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParentFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ParentFindCommand(new ParentNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
