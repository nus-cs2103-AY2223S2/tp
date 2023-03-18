package trackr.logic.parser;


import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditOrderCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.OrderDescriptor;

/**
 * Parses input arguments and creates a new EditOrderCommand object
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditOrderCommand
     * and returns an EditOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNAME, PREFIX_DEADLINE,
                        PREFIX_STATUS, PREFIX_ORDERQUANTITY, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE), pe);
        }

        OrderDescriptor editOrderDescriptor = new OrderDescriptor();
        if (argMultimap.getValue(PREFIX_ORDERNAME).isPresent()) {
            editOrderDescriptor.setOrderName(
                    ParserUtil.parseOrderName(argMultimap.getValue(PREFIX_ORDERNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editOrderDescriptor.setOrderDeadline(
                    ParserUtil.parseOrderDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editOrderDescriptor.setOrderStatus(
                    ParserUtil.parseOrderStatus(argMultimap.getValue(PREFIX_STATUS)));
        }

        if (argMultimap.getValue(PREFIX_ORDERQUANTITY).isPresent()) {
            editOrderDescriptor.setOrderQuantity(
                    ParserUtil.parseOrderQuantity(argMultimap.getValue(PREFIX_ORDERQUANTITY).get()));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editOrderDescriptor.setCustomerName(
                    ParserUtil.parseCustomerName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editOrderDescriptor.setCustomerPhone(
                    ParserUtil.parseCustomerPhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editOrderDescriptor.setCustomerAddress(
                    ParserUtil.parseCustomerAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (!editOrderDescriptor.isAnyFieldNonNull()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }
}
