package trackr.logic.parser.menu;


import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_COST;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PRICE;

import trackr.commons.core.index.Index;
import trackr.logic.commands.menu.EditMenuItemCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.menu.MenuItemDescriptor;

/**
 * Parses input arguments and creates a new EditMenuItemCommand object.
 */
public class EditMenuItemCommandParser implements Parser<EditMenuItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMenuItemCommand
     * and returns an EditMenuItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditMenuItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_COST);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMenuItemCommand.MESSAGE_USAGE), pe);
        }

        MenuItemDescriptor editMenuItemDescriptor = new MenuItemDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editMenuItemDescriptor.setItemName(
                    ParserUtil.parseItemName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editMenuItemDescriptor.setItemPrice(
                    ParserUtil.parseItemPrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_COST).isPresent()) {
            editMenuItemDescriptor.setItemCost(
                    ParserUtil.parseItemCost(argMultimap.getValue(PREFIX_COST).get()));
        }

        if (!editMenuItemDescriptor.isAnyFieldNonNull()) {
            throw new ParseException(EditMenuItemCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMenuItemCommand(index, editMenuItemDescriptor);
    }

}
