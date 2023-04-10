package seedu.address.logic.commands.navigation;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.Messages;
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

        boolean hasModuleTarget = targetModuleCode != null;
        boolean hasLectureTarget = targetLectureName != null;

        if (!hasModuleTarget) {
            throw new CommandException(NavCommand.MESSAGE_NAV_INVALID);
        } else if (!model.hasModule(targetModuleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, targetModuleCode));
        } else if (hasLectureTarget && !model.hasLecture(targetModuleCode, targetLectureName)) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST, targetLectureName, targetModuleCode));
        }

        if (hasLectureTarget) {
            model.navigateTo(targetModuleCode, targetLectureName);
            listAtLecture(targetModuleCode, targetLectureName, model);
        } else {
            model.navigateTo(targetModuleCode);
            listAtModule(targetModuleCode, model);
        }

        return getSuccessfulCommandResult(model.getCurrentNavContext(), model);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DirectNavCommand)) {
            return false;
        }

        DirectNavCommand otherCmd = (DirectNavCommand) other;
        return moduleCode.equals(otherCmd.moduleCode) && lectureName.equals(otherCmd.lectureName);
    }
}
