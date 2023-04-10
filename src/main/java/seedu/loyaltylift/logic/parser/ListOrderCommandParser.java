package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.loyaltylift.logic.commands.ListOrderCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.order.Order;

/**
 * Parses input arguments and creates a new ListOrderCommand object
 */
public class ListOrderCommandParser implements Parser<ListOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListOrderCommand
     * and returns a ListOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT, PREFIX_FILTER);

        Comparator<Order> comparator = Order.SORT_CREATED_DATE;
        if (arePrefixesPresent(argMultimap, PREFIX_SORT)) {
            comparator = ParserUtil.parseOrderSortOption(argMultimap.getValue(PREFIX_SORT).orElse(""));
        }

        Predicate<Order> predicate = PREDICATE_SHOW_ALL_ORDERS;
        if (arePrefixesPresent(argMultimap, PREFIX_FILTER)) {
            predicate = ParserUtil.parseOrderFilterOption(argMultimap.getValue(PREFIX_FILTER).orElse(""));
        }

        return new ListOrderCommand(comparator, predicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
