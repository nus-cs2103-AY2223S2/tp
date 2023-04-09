package trackr.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_CRITERIA;

import trackr.logic.commands.task.SortTasksCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.CriteriaEnum;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.task.SortTasksComparator;

/**
 * Parses input arguments and creates a new SortTasksCommand object.
 */
public class SortTasksCommandParser implements Parser<SortTasksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTasksCommand
     * and returns a SortTasksCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortTasksCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA);

        SortTasksComparator comparator = new SortTasksComparator();
        if (argMultimap.getValue(PREFIX_CRITERIA).isPresent()) {
            comparator.setCriteria(
                    ParserUtil.parseSortingCriteria(argMultimap.getValue(PREFIX_CRITERIA)));
        } else {
            //sort by both status and deadline by default
            comparator.setCriteria(CriteriaEnum.STATUS_AND_DEADLINE);
        }

        return new SortTasksCommand(comparator);
    }
}
