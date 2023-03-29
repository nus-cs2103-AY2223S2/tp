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
 * Views a {@code Fish} identified using it's displayed index from the {@code FishList}.
 */
public class FishViewCommand extends FishCommand {
    public static final String FISH_COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + FISH_COMMAND_WORD
            + ": Displays the Fish identified by the index number used in the displayed Fish List.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + FISH_COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_FISH_SUCCESS = "Viewing Fish: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code FishViewCommand} to view an existing {@code Tank}.
     *
     * @param targetIndex The index of the {@code Fish} to view.
     */
    public FishViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Fish> lastShownList = model.getFilteredFishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
        }

        Fish fishToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredFishList(fish -> fish == fishToView);

        return new CommandResult(String.format(MESSAGE_VIEW_FISH_SUCCESS, fishToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishViewCommand // instanceof handles nulls
                && targetIndex.equals(((FishViewCommand) other).targetIndex)); // state check
    }
}
