package seedu.address.logic.commands.navigation;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.navigation.NavigationContext.NavLayer;

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
            if (navContext.getLayer() == NavLayer.ROOT) {
                navigateToModFromRoot(model);
            } else if (navContext.getLayer() == NavLayer.MODULE) {
                navigateToLecFromMod(model, navContext);
            } else {
                throw new CommandException("Nothing to navigate to!");
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        return getSuccessfulCommandResult(model.getCurrentNavContext(), model);
    }

    private void navigateToModFromRoot(Model model) throws CommandException {
        ModuleCode moduleCode = new ModuleCode(target);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException("The module code provided is invalid!");
        }

        model.navigateToModFromRoot(moduleCode);
        listAtModule(moduleCode, model);
    }

    private void navigateToLecFromMod(Model model, NavigationContext navContext) throws CommandException {
        ModuleCode moduleCode = navContext.getModuleCode();
        LectureName lectureName = new LectureName(target);

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException("The lecture name provided is invalid!");
        }

        model.navigateToLecFromMod(lectureName);
        listAtLecture(moduleCode, lectureName, model);
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
