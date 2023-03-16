package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import trackr.logic.commands.AddOrderCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.Customer;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * Parser for add order command
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNAME, PREFIX_ORDERQUANTITY,
                        PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ORDERNAME, PREFIX_ORDERQUANTITY, PREFIX_DEADLINE,
                PREFIX_STATUS, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        OrderName orderName = ParserUtil.parseOrderName(argMultimap.getValue(PREFIX_ORDERNAME).get());
        OrderQuantity orderQuantity = ParserUtil.parseOrderQuantity(argMultimap.getValue(PREFIX_ORDERQUANTITY).get());
        OrderDeadline orderDeadline = ParserUtil.parseOrderDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        OrderStatus orderStatus = ParserUtil.parseOrderStatus(argMultimap.getValue(PREFIX_STATUS));
        CustomerName customerName = ParserUtil.parseCustomerName(argMultimap.getValue(PREFIX_NAME).get());
        CustomerPhone customerPhone = ParserUtil.parseCustomerPhone(argMultimap.getValue(PREFIX_PHONE).get());
        CustomerAddress customerAddress = ParserUtil.parseCustomerAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Customer customer = new Customer(customerName, customerPhone, customerAddress);
        Order order = new Order(orderName, orderDeadline, orderStatus, orderQuantity, customer);

        return new AddOrderCommand(order);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
