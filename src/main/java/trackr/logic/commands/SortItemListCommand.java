package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Sort all items in the item list by a given criteria.
 */

public class SortItemListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "%s List sorted";

    private final ModelEnum modelEnum;

    /**
     * Creates an SortItemListCommand to sort the item list.
     */
    public SortItemListCommand(ModelEnum modelEnum) {
        requireNonNull(modelEnum);
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortItemList(modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum.toString().toLowerCase()));
    }
}
