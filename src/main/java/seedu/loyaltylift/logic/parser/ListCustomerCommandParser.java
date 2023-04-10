package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.customer.Customer;

/**
 * Parses input arguments and creates a new ListCustomerCommand object
 */
public class ListCustomerCommandParser implements Parser<ListCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCustomerCommand
     * and returns a ListCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT, PREFIX_FILTER);

        Comparator<Customer> comparator = Customer.SORT_NAME;
        if (arePrefixesPresent(argMultimap, PREFIX_SORT)) {
            comparator = ParserUtil.parseCustomerSortOption(argMultimap.getValue(PREFIX_SORT).orElse(""));
        }

        Predicate<Customer> predicate = PREDICATE_SHOW_ALL_CUSTOMERS;
        if (arePrefixesPresent(argMultimap, PREFIX_FILTER)) {
            predicate = ParserUtil.parseCustomerFilterOption(argMultimap.getValue(PREFIX_FILTER).orElse(""));
        }

        return new ListCustomerCommand(comparator, predicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
