package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Deletes a lecture identified using its name, within a module
 */
public class DeleteLectureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            "(2) Deletes the lecture of the module identified by the lecture name and module code.\n"
            + "Parameters: lecture name, module code\n"
            + "Example: " + COMMAND_WORD + " Lecture 01 " + PREFIX_MODULE + " CS2103/T\n";

    public static final String MESSAGE_DELETE_LECTURE_SUCCESS = "Deleted Lecture: %1$s from Module %2$s";

    private final ModuleCode moduleCode;
    private final LectureName targetLectureName;

    /**
     * Creates a Delete Lecture Command executable that deletes a lecture with {@code targetLectureName}
     * from module of {@code moduleCode}
     *
     * @param targetLectureName Name of lecture to delete
     * @param moduleCode Module Code of module that lecture is within
     */
    public DeleteLectureCommand(LectureName targetLectureName, ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
        this.targetLectureName = targetLectureName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        if (!model.hasLecture(moduleCode, targetLectureName)) {
            throw new CommandException(String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST, targetLectureName, moduleCode));
        }

        ReadOnlyModule module = model.getModule(moduleCode);
        ReadOnlyLecture lectureToDelete = model.getLecture(moduleCode, targetLectureName);

        model.deleteLecture(module, lectureToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_LECTURE_SUCCESS, lectureToDelete, moduleCode));

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteLectureCommand)
                && targetLectureName.equals(((DeleteLectureCommand) other).targetLectureName);
    }

}
