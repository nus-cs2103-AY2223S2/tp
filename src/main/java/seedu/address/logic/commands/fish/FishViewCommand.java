package seedu.address.logic.commands.fish;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;

/**
 * Deletes a {@code Fish} identified using it's displayed index from the {@code FishList}.
 */
public class FishViewCommand extends FishCommand {
    public static final String FISH_COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + FISH_COMMAND_WORD
            + ": Displays the Fish identified by the index number used in the displayed Fish List.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + FISH_COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_TANK_SUCCESS = "Viewed Fish: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code TankDeleteCommand} to delete an existing {@code Tank}.
     *
     * @param targetIndex The index of the {@code Tank} to delete.
     */
    public FishViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Fish> lastShownList = model.getFilteredFishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TANK_DISPLAYED_INDEX);
        }

        Fish fishToView = lastShownList.get(targetIndex.getZeroBased());
        //DISPLAY tankToView TO UI @avery

        return new CommandResult(String.format(MESSAGE_VIEW_TANK_SUCCESS, fishToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((FishViewCommand) other).targetIndex)); // state check
    }
}
