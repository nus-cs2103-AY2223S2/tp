package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;


/**
 * Lists all pets in the pet list to the user.
 */
public class ChangeCostCommand extends Command {

    public static final String COMMAND_WORD = "changecost";
    public static final String SHORTCUT_WORD = "cc";

    public static final String MESSAGE_SUCCESS = "Changed cost calculation of pet!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the rate and flat cost identified by the index number used in the displayed pet list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 0.10 0.50";


    final Index targetIndex;
    final double rate;
    final double flatCost;

    /**
     * Constructs a {@code ChangeCostCommand}
     * @param targetIndex Index
     * @param rate double
     * @param flatCost double
     */
    public ChangeCostCommand(Index targetIndex, double rate, double flatCost) {
        this.targetIndex = targetIndex;
        this.rate = rate;
        this.flatCost = flatCost;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Pet> lastShownList = model.getFilteredPetList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToChange = lastShownList.get(targetIndex.getZeroBased());
        petToChange.getCost().setRate(rate);
        petToChange.getCost().setFlatCost(flatCost);
        return new CommandResult(String.format(MESSAGE_SUCCESS, petToChange));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeCostCommand // instanceof handles nulls
                && targetIndex.equals(((ChangeCostCommand) other).targetIndex)); // state check
    }
}
