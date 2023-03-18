package seedu.address.logic.parser.task;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.todo.ContentContainsKeywordsPredicate;
import seedu.address.model.todo.TitleContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        TitleContainsKeywordsPredicate titlePredicate = new TitleContainsKeywordsPredicate(
                Arrays.asList(nameKeywords));
        ContentContainsKeywordsPredicate contentPredicate = new ContentContainsKeywordsPredicate(
                Arrays.asList(nameKeywords));

        return new FindTaskCommand(titlePredicate, contentPredicate);
    }

}
