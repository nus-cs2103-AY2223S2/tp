package seedu.address.logic.commands.tank;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;
import seedu.address.model.task.Task;

/**
 * Deletes a {@code Tank} identified using it's displayed index from the {@code TankList}.
 */
public class TankDeleteCommand extends TankCommand {

    public static final String TANK_COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TANK_COMMAND_WORD
            + ": Deletes the Tank identified by the index number used in the displayed Tank List.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + TANK_COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TANK_SUCCESS = "Deleted Tank: %1$s";
    public static final String MESSAGE_DELETE_TANK_FAILURE = "You can't delete this tank as "
            + "there are fishes and tasks still present!\n"
            + "Delete the fishes and tasks in this tank before deleting this tank.";

    private final Index targetIndex;

    /**
     * Constructs an {@code TankDeleteCommand} to delete an existing {@code Tank}.
     *
     * @param targetIndex The index of the {@code Tank} to delete.
     */
    public TankDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tank> lastShownList = model.getFilteredTankList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_TANK_INDEX_OUTOFBOUNDS);
        }

        Tank tankToDelete = lastShownList.get(targetIndex.getZeroBased());

        /* Throw exception if fishes are still present in tank */
        if (tankToDelete.getFishList().size() > 0) {
            throw new CommandException(MESSAGE_DELETE_TANK_FAILURE);
        }

        /* Throw exception if tasks are still present in tank */
        List<Task> taskList = model.getFilteredTaskList();
        for (Task task : taskList) {
            if (task.isTankRelatedTask() && tankToDelete.isSameTank(task.getTank())) {
                throw new CommandException(MESSAGE_DELETE_TANK_FAILURE);
            }
        }

        model.deleteIndividualReadingLevels(tankToDelete.getReadingLevels());
        model.deleteTank(tankToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TANK_SUCCESS, tankToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((TankDeleteCommand) other).targetIndex)); // state check
    }
}

