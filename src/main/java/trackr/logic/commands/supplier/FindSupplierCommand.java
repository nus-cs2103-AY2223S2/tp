package trackr.logic.commands.supplier;

import trackr.logic.commands.FindItemCommand;
import trackr.model.ModelEnum;
import trackr.model.person.PersonNameContainsKeywordsPredicate;
import trackr.model.person.Supplier;

/**
 * Finds and lists all suppliers in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindSupplierCommand extends FindItemCommand<Supplier> {

    public static final String COMMAND_WORD = "find_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "find_s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all supplier whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    /**
     * Creates an FindSupplierCommand to find all {@code Supplier} that match the predicate.
     *
     * @param predicate The predicate to find the suppliers with.
     */
    public FindSupplierCommand(PersonNameContainsKeywordsPredicate predicate) {
        super(predicate, ModelEnum.SUPPLIER);
    }
}
