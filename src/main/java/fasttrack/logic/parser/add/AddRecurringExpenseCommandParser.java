package fasttrack.logic.parser.add;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_END_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_START_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;

import java.time.LocalDate;
import java.util.stream.Stream;

import fasttrack.logic.commands.add.AddRecurringExpenseCommand;
import fasttrack.logic.parser.ArgumentMultimap;
import fasttrack.logic.parser.ArgumentTokenizer;
import fasttrack.logic.parser.Parser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.Prefix;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Price;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;

/**
 * Parses input arguments and creates a new AddCategoryCommand object
 */
public class AddRecurringExpenseCommandParser implements Parser<AddRecurringExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCategoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecurringExpenseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_PRICE , PREFIX_START_DATE,
                         PREFIX_END_DATE, PREFIX_TIMESPAN);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY, PREFIX_START_DATE, PREFIX_PRICE, PREFIX_TIMESPAN,
                PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurringExpenseCommand.MESSAGE_USAGE));
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        String name = ParserUtil.parseExpenseName(argMultimap.getValue(PREFIX_NAME).get());
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        RecurringExpenseType timespan = ParserUtil.parseTimeSpanRecurringExpense(
                argMultimap.getValue(PREFIX_TIMESPAN).get());
        LocalDate endDate = null;
        if (arePrefixesPresent(argMultimap, PREFIX_END_DATE)) {
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
            if (endDate.isBefore(startDate)) {
                throw new ParseException("End date provided is earlier than start date.");
            }
            RecurringExpenseManager toAdd = new RecurringExpenseManager(name, price,
                    category, startDate, endDate, timespan);
            return new AddRecurringExpenseCommand(toAdd);
        }

        RecurringExpenseManager toAdd = new RecurringExpenseManager(name, price,
                category, startDate, timespan);
        return new AddRecurringExpenseCommand(toAdd);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
