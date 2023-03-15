package trackr.logic.parser;

import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import trackr.logic.commands.FindTaskCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_STATUS);

        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] taskNameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicate.setTaskNameKeywords(Arrays.asList(taskNameKeywords));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            predicate.setTaskDeadline(
                    ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            predicate.setTaskStatus(
                    ParserUtil.parseTaskStatus(argMultimap.getValue(PREFIX_STATUS)));
        }

        if (!predicate.isAnyFieldPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        return new FindTaskCommand(predicate);
    }
}
