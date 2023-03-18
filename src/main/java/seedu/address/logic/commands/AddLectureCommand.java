package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Adds a lecture to a module.
 */
public class AddLectureCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New lecture added to module %s: %s";
    public static final String MESSAGE_DUPLICATE_LECTURE = "This lecture already exists in module %s.";

    private final ModuleCode moduleCode;
    private final Lecture toAdd;

    /**
     * Creates an {@code AddLectureCommand} to add {@code lecture} to the module with code {@code moduleCode}.
     *
     * @param moduleCode The code of the module to add the lecture to.
     * @param lecture The lecture to be added.
     */
    public AddLectureCommand(ModuleCode moduleCode, Lecture lecture) {
        requireAllNonNull(moduleCode, lecture);

        this.moduleCode = moduleCode;
        toAdd = lecture;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        ReadOnlyModule module = model.getTracker().getModule(moduleCode);

        if (model.hasLecture(module, toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_LECTURE, moduleCode));
        }

        model.addLecture(module, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddLectureCommand)) {
            return false;
        }

        AddLectureCommand otherCommand = (AddLectureCommand) other;

        return moduleCode.equals(otherCommand.moduleCode)
                && toAdd.equals(otherCommand.toAdd);
    }

}
