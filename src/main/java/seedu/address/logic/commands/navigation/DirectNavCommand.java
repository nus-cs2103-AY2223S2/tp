package seedu.address.logic.commands.navigation;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;

/**
 * Navigates directly to module or lecture regardless of current context.
 */
public class DirectNavCommand extends NavCommand {

    private final Optional<ModuleCode> moduleCode;
    private final Optional<LectureName> lectureName;

    /**
     * Constructs a command that directly navigates to the specified {@code moduleCode} or {@code lectureName}.
     * @param moduleCode moduleCode of target module
     * @param lectureName lectureName of target lecture
     */
    public DirectNavCommand(Optional<ModuleCode> moduleCode, Optional<LectureName> lectureName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        NavigationContext navContext = model.getCurrentNavContext();
        ModuleCode targetModuleCode = moduleCode.orElseGet(navContext::getModuleCode);
        LectureName targetLectureName = lectureName.orElse(null);

        if (targetModuleCode == null || !model.hasModule(targetModuleCode)) {
            throw new CommandException("The module code provided is invalid!");
        }

        if (targetLectureName == null) {
            model.navigateTo(targetModuleCode);
        } else if (model.hasLecture(targetModuleCode, targetLectureName)) {
            model.navigateTo(targetModuleCode, targetLectureName);
        } else {
            throw new CommandException("The lecture name provided is invalid!");
        }

        return new CommandResult(getSuccessfulNavMessage(model.getCurrentNavContext()));
    }
}
