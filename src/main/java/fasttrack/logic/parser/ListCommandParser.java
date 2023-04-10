package fasttrack.logic.parser;


import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;

import java.util.Optional;

import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.expense.ExpenseInCategoryPredicate;
import fasttrack.model.expense.ExpenseInTimespanPredicate;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class ListCommandParser implements Parser<ListExpensesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListExpensesCommand parse(String args) throws ParseException {

        if (args.equals("")) {
            return new ListExpensesCommand(Optional.empty(), Optional.empty());
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_TIMESPAN);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpensesCommand.MESSAGE_USAGE));
        }

        Optional<String> categoryStringArg = argMultimap.getValue(PREFIX_CATEGORY);
        Optional<String> timespanStringArg = argMultimap.getValue(PREFIX_TIMESPAN);

        ExpenseInCategoryPredicate categoryPredicate = null;
        ExpenseInTimespanPredicate timespanPredicate = null;

        if (categoryStringArg.isPresent()) {
            categoryPredicate = new ExpenseInCategoryPredicate(ParserUtil.parseCategory(categoryStringArg.get()));
        }
        if (timespanStringArg.isPresent()) {
            timespanPredicate = new ExpenseInTimespanPredicate(ParserUtil.parseTimespan(timespanStringArg.get()));
        }


        return new ListExpensesCommand(
                Optional.ofNullable(categoryPredicate),
                Optional.ofNullable(timespanPredicate));
    }
}
