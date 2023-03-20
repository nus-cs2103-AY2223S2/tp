package seedu.loyaltylift.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.commands.AddOrderCommand.MESSAGE_USAGE;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.AddOrderCommand;
import seedu.loyaltylift.logic.commands.AddOrderCommand.AddOrderDescriptor;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.attribute.Name;

/**
 * Parses input arguments and creates a new AddOrderCommand object
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_QUANTITY, PREFIX_ADDRESS);
        Index customerIndex;
        Name orderName;

        try {
            customerIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            orderName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        AddOrderDescriptor addOrderDescriptor = new AddOrderDescriptor();
        addOrderDescriptor.setName(orderName);
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            addOrderDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            addOrderDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        return new AddOrderCommand(customerIndex, addOrderDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
