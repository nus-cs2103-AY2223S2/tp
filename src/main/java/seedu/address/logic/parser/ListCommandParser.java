package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESPAN;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.list.ListExpensesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.ExpenseInCategoryPredicate;
import seedu.address.model.expense.ExpenseInTimespanPredicate;

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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
