package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Clears the item list.
 */
public abstract class ClearItemCommand<T extends Item> extends Command {

    public static final String MESSAGE_SUCCESS = "%s list has been cleared!";

    private final ModelEnum modelEnum;

    /**
     * Creates an ClearItemCommand to clear the specified item list.
     *
     * @param modelEnum A representation of the name of the list to clear.
     */
    public ClearItemCommand(ModelEnum modelEnum) {
        requireNonNull(modelEnum);
        this.modelEnum = modelEnum;
    }

    /**
     * Clears the specified item list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Success message of the clear operation for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemList(modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum));
    }
}
