package seedu.address.logic.commands.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;
import static seedu.address.model.Model.SHOW_FISHES_IN_TANK;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;

/**
 * Sorts {@code Fish} using an attribute from the given {@code FishList}.
 */
public class FishSortCommand extends FishCommand {
    public static final String FISH_COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + FISH_COMMAND_WORD
            + ": Sorts the fish list by the given attribute.\n"
            + "Parameters: "
            + PREFIX_SORT_BY + "ATTRIBUTE "
            + "[" + PREFIX_TANK + "TANK INDEX]...\n"
            + "Example: " + COMMAND_WORD + " " + FISH_COMMAND_WORD + " "
            + PREFIX_SORT_BY + "lfd "
            + PREFIX_TANK + "1\n"
            + "Sorting Options: n/lfd/s/fi/tk";

    public static final String MESSAGE_VIEW_FISH_SUCCESS = "Showing sorted Fish";

    private final Comparator<Fish> fishComparator;
    private final Index tankIndex;

    /**
     * Constructs an {@code FishSortCommand} to view an existing {@code Tank}.
     *
     * @param fishComparator The index of the {@code Fish} to view.
     * @param tankIndex Optional tankIndex to sort by specified tank.
     */
    public FishSortCommand(Comparator<Fish> fishComparator, Index tankIndex) {
        requireNonNull(fishComparator);
        this.fishComparator = fishComparator;
        this.tankIndex = tankIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredFishList(fishComparator);

        // If optional tank index was given, display sorting by tank
        if (tankIndex != null) {
            List<Tank> lastShownList = model.getFilteredTankList();
            if (tankIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_TANK_INDEX_OUTOFBOUNDS);
            }
            Tank tankToView = lastShownList.get(tankIndex.getZeroBased());
            // display filtered fish only
            model.updateFilteredFishList(SHOW_FISHES_IN_TANK.apply(tankToView));
        }
        return new CommandResult(MESSAGE_VIEW_FISH_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishSortCommand // instanceof handles nulls
                && fishComparator.equals(((FishSortCommand) other).fishComparator)); // state check
    }
}
