package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;

/**
 * Clears the Dengue Hotspot Tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Dengue Hotspot Tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDengueHotspotTracker(new DengueHotspotTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
