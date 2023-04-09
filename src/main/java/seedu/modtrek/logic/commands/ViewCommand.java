package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.degreedata.DegreeProgressionData;

/**
 * View the data progress or module list
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_PROGRESS_SUCCESS = "Displaying the progress,\n"
            + "Here is an overview:\n\n";

    public static final String MESSAGE_MODULES_SUCCESS = "Listed all modules";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches between different viewing screens.\n\n"
            + "Parameters: progress or modules\n\n"
            + "Example 1: view progress\n"
            + "Example 2: view modules";

    private final boolean isProgress;

    public ViewCommand(boolean isProgress) {
        this.isProgress = isProgress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isProgress) {
            DegreeProgressionData details = model.generateData();
            if (details.isValid()) {
                return new CommandResult(MESSAGE_PROGRESS_SUCCESS + details, false, true, false, false);
            } else {
                throw new CommandException(DegreeProgressionData.ERROR);
            }
        } else {
            return new CommandResult(MESSAGE_MODULES_SUCCESS);
        }
    }
}
