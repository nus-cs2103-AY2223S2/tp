package trackr.logic.parser.order;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import trackr.logic.commands.order.AddOrderCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.Prefix;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

/**
 * Parses input arguments and creates a new AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNAME, PREFIX_ORDERQUANTITY,
                        PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ORDERNAME, PREFIX_ORDERQUANTITY, PREFIX_DEADLINE,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        OrderName orderName = ParserUtil.parseOrderName(argMultimap.getValue(PREFIX_ORDERNAME).get());
        OrderQuantity orderQuantity = ParserUtil.parseOrderQuantity(argMultimap.getValue(PREFIX_ORDERQUANTITY).get());
        OrderDeadline orderDeadline = ParserUtil.parseOrderDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        OrderStatus orderStatus = ParserUtil.parseOrderStatus(argMultimap.getValue(PREFIX_STATUS));
        PersonName customerName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PersonPhone customerPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        PersonAddress customerAddress = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Customer customer = new Customer(customerName, customerPhone, customerAddress);
        Order order = new Order(orderName, orderDeadline, orderStatus, orderQuantity, customer);

        return new AddOrderCommand(order);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
