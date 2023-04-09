package trackr.logic.commands.menu;

import trackr.logic.commands.FindItemCommand;
import trackr.model.ModelEnum;
import trackr.model.menu.ItemNameContainsKeywordsPredicate;
import trackr.model.menu.MenuItem;

/**
 * Finds and lists all item in menu whose description contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindMenuItemCommand extends FindItemCommand<MenuItem> {

    public static final String COMMAND_WORD = "find_item";
    public static final String COMMAND_WORD_SHORTCUT = "find_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all menu items whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " chocolate cookies";

    /**
     * Creates an FindMenuItemCommand to find all {@code MenuItem} that match the predicate.
     *
     * @param predicate The predicate to find the menu items with.
     */
    public FindMenuItemCommand(ItemNameContainsKeywordsPredicate predicate) {
        super(predicate, ModelEnum.MENUITEM);
    }
}
