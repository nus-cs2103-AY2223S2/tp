package fasttrack.logic.parser.add;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import fasttrack.logic.commands.add.AddExpenseCommand;
import fasttrack.logic.parser.ArgumentMultimap;
import fasttrack.logic.parser.ArgumentTokenizer;
import fasttrack.logic.parser.Parser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.Prefix;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.Price;

/**
 * Parses input arguments and creates a new AddExpenseCommand object
 */
public class AddExpenseCommandParser implements Parser<AddExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExpenseCommand
     * and returns an AddExpenseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExpenseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_PRICE, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenseCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseExpenseName(argMultimap.getValue(PREFIX_NAME).get());
        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Price amount = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());

        Optional<String> dateStringArg = argMultimap.getValue(PREFIX_DATE);

        LocalDate date = LocalDate.now();
        if (dateStringArg.isPresent()) {
            date = ParserUtil.parseDate(dateStringArg.get());
        }

        Expense expenseToAdd = new Expense(name, amount, date, category);
        return new AddExpenseCommand(expenseToAdd);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
