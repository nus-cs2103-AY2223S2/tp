package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.fitbook.logic.commands.FindRoutineCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindRoutineCommandParser implements Parser<FindRoutineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRoutineCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoutineCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindRoutineCommand(new RoutineNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
