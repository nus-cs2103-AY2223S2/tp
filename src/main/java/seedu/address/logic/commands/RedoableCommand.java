package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.shop.Shop;

/**
 * This class enables command to be able to undo / redo
 */
public abstract class RedoableCommand extends Command {

    private ReadOnlyShop previousShop;
    private String saveSuccessMessage = "";

    protected abstract CommandResult executeUndoableCommand(Model model) throws CommandException;

    /**
     * Stores the current state of {@code model#addressBook}.
     */
    private void saveShopSnapshot(Model model) {
        requireNonNull(model);
        this.previousShop = new Shop(model.getShop());
    }

    /**
     * Reverts the Shop to the state before this command
     * was executed and updates the filtered person list to
     * show all persons.
     */
    protected final void undo(Model model) {
        requireAllNonNull(model, this.previousShop);

        model.setShop(this.previousShop);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Executes the command and updates the filtered person
     * list to show all persons.
     */
    protected final void redo(Model model) {
        requireNonNull(model);
        try {
            executeUndoableCommand(model);
        } catch (CommandException ce) {
            throw new AssertionError("The command has been successfully executed previously; "
                                             + "it should not fail now");
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public final CommandResult execute(Model model) throws CommandException {
        saveShopSnapshot(model);
        return executeUndoableCommand(model);
    }

}
