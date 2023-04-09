package trackr.logic.parser.menu;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_COST;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.stream.Stream;

import trackr.logic.commands.menu.AddMenuItemCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.Prefix;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;

/**
 * Parses input arguments and creates a new AddMenuItemCommand object.
 */

public class AddMenuItemCommandParser implements Parser<AddMenuItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMenuItemCommand
     * and returns an AddMenuItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddMenuItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_COST)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuItemCommand.MESSAGE_USAGE));
        }

        ItemName itemName = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_NAME).get());
        ItemSellingPrice itemPrice = ParserUtil.parseItemPrice(argMultimap.getValue(PREFIX_PRICE).get());
        ItemCost itemCost = ParserUtil.parseItemCost(argMultimap.getValue(PREFIX_COST).get());
        MenuItem menuItem = new MenuItem(itemName, itemPrice, itemCost);

        return new AddMenuItemCommand(menuItem);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
