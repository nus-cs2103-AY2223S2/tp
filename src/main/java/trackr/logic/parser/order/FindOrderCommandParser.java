package trackr.logic.parser.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import trackr.logic.commands.order.FindOrderCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.OrderContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindOrderCommand object.
 */
//@@author changgittyhub-reused
public class FindOrderCommandParser implements Parser<FindOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNAME, PREFIX_DEADLINE,
                        PREFIX_STATUS, PREFIX_ORDERQUANTITY,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS);

        OrderContainsKeywordsPredicate predicate = new OrderContainsKeywordsPredicate();
        if (argMultimap.getValue(PREFIX_ORDERNAME).isPresent()) {
            String[] orderNameKeywords = argMultimap.getValue(PREFIX_ORDERNAME).get().trim().split("\\s+");
            predicate.setOrderNameKeywords(Arrays.asList(orderNameKeywords));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            predicate.setOrderDeadline(
                    ParserUtil.parseOrderDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            predicate.setOrderStatus(
                    ParserUtil.parseOrderStatus(argMultimap.getValue(PREFIX_STATUS)));
        }
        if (argMultimap.getValue(PREFIX_ORDERQUANTITY).isPresent()) {
            predicate.setOrderQuantity(
                    ParserUtil.parseOrderQuantity(argMultimap.getValue(PREFIX_ORDERQUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] customerNameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicate.setCustomerNameKeywords(Arrays.asList(customerNameKeywords));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate.setCustomerPhone(
                    ParserUtil.parseCustomerPhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            predicate.setCustomerAddress(
                    ParserUtil.parseCustomerAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }


        if (!predicate.isAnyFieldPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        return new FindOrderCommand(predicate);
    }

}
