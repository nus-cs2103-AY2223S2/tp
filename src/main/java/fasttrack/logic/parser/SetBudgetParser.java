package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.stream.Stream;

import fasttrack.logic.commands.SetBudgetCommand;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.Budget;
import fasttrack.model.expense.Price;

/**
 * Parses input arguments and creates a new AddExpenseCommand object
 */
public class SetBudgetParser implements Parser<SetBudgetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExpenseCommand
     * and returns an AddExpenseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE));
        }

        Price amount = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Budget budget = new Budget(amount.getPriceAsDouble());

        return new SetBudgetCommand(budget);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
