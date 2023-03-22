package seedu.address.logic.commands.fish;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;

/**
 * Sorts {@code Fish} using an attribute from the given {@code FishList}.
 */
public class FishSortCommand extends FishCommand {
    public static final String FISH_COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + FISH_COMMAND_WORD
            + ": Sorts the Fish by the given attribute in the displayed Fish List.\n"
            + "Parameters: n/lfd/s/fi/tk (must be a valid fish parameter)\n"
            + "Example: " + COMMAND_WORD + " " + FISH_COMMAND_WORD + " lfd";

    public static final String MESSAGE_VIEW_FISH_SUCCESS = "Sorting Fish by: %1$s";

    private final Comparator<Fish> fishComparator;

    /**
     * Constructs an {@code FishSortCommand} to view an existing {@code Tank}.
     *
     * @param fishComparator The index of the {@code Fish} to view.
     */
    public FishSortCommand(Comparator<Fish> fishComparator) {
        this.fishComparator = fishComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Fish> lastShownList = model.getFilteredFishList();


        model.sortFilteredFishList(fishComparator);
        //model.updateFilteredFishList(x -> true);

        return new CommandResult(String.format(MESSAGE_VIEW_FISH_SUCCESS, null));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishSortCommand // instanceof handles nulls
                && fishComparator.equals(((FishSortCommand) other).fishComparator)); // state check
    }
}
