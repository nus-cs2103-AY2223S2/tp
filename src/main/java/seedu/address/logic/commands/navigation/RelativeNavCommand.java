package seedu.address.logic.commands.navigation;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;

/**
 * Navigates to a target which can be a module or lecture depending on current context.
 */
public class RelativeNavCommand extends NavCommand {

    private final String target;

    public RelativeNavCommand(String target) {
        this.target = target;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        NavigationContext navContext = model.getCurrentNavContext();

        try {
            if (navContext.getModuleCode() == null) {
                ModuleCode moduleCode = new ModuleCode(target);
                if (!model.hasModule(moduleCode)) {
                    throw new CommandException("The module code provided is invalid!");
                }
                model.navigateToModFromRoot(moduleCode);
            } else if (navContext.getLectureName() == null) {
                LectureName lectureName = new LectureName(target);
                if (!model.hasLecture(navContext.getModuleCode(), lectureName)) {
                    throw new CommandException("The lecture name provided is invalid!");
                }
                model.navigateToLecFromMod(lectureName);
            } else {
                throw new CommandException("Nothing to navigate to!");
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(getSuccessfulNavMessage(model.getCurrentNavContext()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RelativeNavCommand)) {
            return false;
        }

        RelativeNavCommand otherCmd = (RelativeNavCommand) other;
        return target.equals(otherCmd.target);
    }
}
