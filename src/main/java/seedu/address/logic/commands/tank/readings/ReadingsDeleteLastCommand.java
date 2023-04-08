package seedu.address.logic.commands.tank.readings;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tank.TankCommand;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.Reading;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * Parses input arguments and creates a new ReadingsDeleteLastCommand object
 */
public class ReadingsDeleteLastCommand extends TankCommand {
    public static final String TANK_COMMAND_WORD = "delLastReadings";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TANK_COMMAND_WORD
            + ": Deletes the last readings of each type of the tank specified by the given index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + TANK_COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted set of readings: %1$s, %2$s, %3$s";
    public static final String MESSAGE_DELETE_READING_FAILURE = "You can't delete readings from this tank as "
            + "there are no readings present!\n"
            + "Add some readings before trying to delete readings.";

    private final Index targetIndex;

    public ReadingsDeleteLastCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tank> lastShownList = model.getFilteredTankList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_TANK_INDEX_OUTOFBOUNDS);
        }

        Tank tankToDeleteFrom = model.getFilteredTankList().get(targetIndex.getZeroBased());
        UniqueIndividualReadingLevels individualReadingLevels = tankToDeleteFrom.getReadingLevels();
        /* Throw exception if fishes are still present in tank */
        if (individualReadingLevels.size() == 0) {
            throw new CommandException(MESSAGE_DELETE_READING_FAILURE);
        }

        Reading[] removed = model.deleteLastEntryFromIndividualReadings(individualReadingLevels);
        return new CommandResult(String.format(MESSAGE_SUCCESS, removed[0], removed[1], removed[2]));
    }

}
