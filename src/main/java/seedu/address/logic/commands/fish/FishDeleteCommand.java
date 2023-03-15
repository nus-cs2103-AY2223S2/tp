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
 * Deletes a fish identified using it's displayed index from the address book.
 */
public class FishDeleteCommand extends FishCommand {

    public static final String FISH_COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the fish identified by the index number used in the displayed fish list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FISH_SUCCESS = "Deleted Fish: %1$s";

    private final Index targetIndex;

    public FishDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Fish> lastShownList = model.getFilteredFishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
        }

        Fish fishToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFish(fishToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FISH_SUCCESS, fishToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((FishDeleteCommand) other).targetIndex)); // state check
    }
}
