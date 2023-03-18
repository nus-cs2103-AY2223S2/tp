package seedu.address.logic.commands.tank;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.SHOW_FISHES_IN_TANK;

import java.util.List;

import seedu.address.commons.core.GuiSettings.GuiMode;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;

/**
 * Deletes a {@code Tank} identified using it's displayed index from the {@code TankList}.
 */
public class TankViewCommand extends TankCommand {

    public static final String TANK_COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TANK_COMMAND_WORD
            + ": Displays the Tank identified by the index number used in the displayed Tank List.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + TANK_COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_TANK_SUCCESS = "Viewing Tank: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code TankDeleteCommand} to delete an existing {@code Tank}.
     *
     * @param targetIndex The index of the {@code Tank} to delete.
     */
    public TankViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tank> lastShownList = model.getFilteredTankList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TANK_DISPLAYED_INDEX);
        }

        Tank tankToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredFishList(SHOW_FISHES_IN_TANK.apply(tankToView));
        model.setGuiMode(GuiMode.DISPLAY_FISHES_TASKS);

        return new CommandResult(String.format(MESSAGE_VIEW_TANK_SUCCESS, tankToView), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankViewCommand // instanceof handles nulls
                && targetIndex.equals(((TankViewCommand) other).targetIndex)); // state check
    }
}

