package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.commons.core.Messages;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

import java.util.function.Predicate;

/**
 * Sorts all items in the list.
 */
public class SortItemsCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all %ss";

    private final ModelEnum modelEnum;
    private final Predicate<Item> predicate;

    /**
     * Creates an SortItemsCommand to sort all the items.
     */
    public SortItemsCommand(Predicate<Item> predicate, ModelEnum modelEnum) {
        requireNonNull(modelEnum);
        this.predicate = predicate;
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate, modelEnum);
        return new CommandResult(String.format(Messages.MESSAGE_ITEMS_SORTED,
                model.getFilteredItemList(modelEnum).size(),
                modelEnum.toString().toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortItemsCommand // instanceof handles nulls
                && predicate.equals(((SortItemsCommand) other).predicate)); // state check
    }
}
