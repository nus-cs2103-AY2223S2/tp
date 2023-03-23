package trackr.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import trackr.logic.commands.task.FindTaskCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SortTaskCommand object
 */
public class SortTaskCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskCommand
     * and returns a SortTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        return new SortTaskCommand();
    }
}
