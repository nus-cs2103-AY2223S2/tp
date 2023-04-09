package trackr.logic.commands.menu;

import static trackr.logic.parser.CliSyntax.PREFIX_COST;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PRICE;

import trackr.logic.commands.AddItemCommand;
import trackr.model.ModelEnum;
import trackr.model.menu.MenuItem;

/**
 * Adds a menuItem to the Menu.
 */
public class AddMenuItemCommand extends AddItemCommand<MenuItem> {
    public static final String COMMAND_WORD = "add_item";
    public static final String COMMAND_WORD_SHORTCUT = "add_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the menu. "
            + "Parameters: "
            + PREFIX_NAME + "ITEM NAME "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_COST + "COST \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Chocolate Cookies "
            + PREFIX_PRICE + "2 "
            + PREFIX_COST + "0.50 ";

    /**
     * Creates an AddMenuItemCommand to add the specified {@code MenuItem}.
     */
    public AddMenuItemCommand(MenuItem menuItem) {
        super(menuItem, ModelEnum.MENUITEM);
    }
}
