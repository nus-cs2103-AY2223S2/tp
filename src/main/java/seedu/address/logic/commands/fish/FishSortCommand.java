package seedu.address.logic.commands.fish;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.GuiSettings;
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

    public static final String MESSAGE_VIEW_FISH_SUCCESS = "Showing sorted Fish";

    private final Comparator<Fish> fishComparator;

    /**
     * Constructs an {@code FishSortCommand} to view an existing {@code Tank}.
     *
     * @param fishComparator The index of the {@code Fish} to view.
     */
    public FishSortCommand(Comparator<Fish> fishComparator) {
        requireNonNull(fishComparator);
        this.fishComparator = fishComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredFishList(fishComparator);
        model.setGuiMode(GuiSettings.GuiMode.DISPLAY_SORTED_FISHES);
        return new CommandResult(MESSAGE_VIEW_FISH_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishSortCommand // instanceof handles nulls
                && fishComparator.equals(((FishSortCommand) other).fishComparator)); // state check
    }
}
