package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Level;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.LecturePredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all modules, lectures or videos.\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "cs2103 "
            + PREFIX_LECTURE + "lecture-1 ";

    public static final String MESSAGE_SUCCESS_MODULES = "Listed all modules";

    public static final String MESSAGE_SUCCESS_LECTURES = "Listed all lectures from module: %s";

    public static final String MESSAGE_SUCCESS_VIDEOS = "Listed all videos from module: %s, lecture: %s";

    public static final String MESSAGE_FAIL_CODE = "Module code format is invalid";

    private ModuleCode moduleCode;

    private LectureName lectureName;

    private Exception error;

    /**
     * Creates an empty ListCommand
     */
    public ListCommand() {}

    /**
     * Creates an ListCommand to add the specified {@code ModuleCode}
     */
    public ListCommand(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Creates an ListCommand to add the specified {@code ModuleCode, LectureName}
     */
    public ListCommand(ModuleCode moduleCode, LectureName lectureName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
    }

    /**
     * Creates an ListCommand with invalid inputs
     */
    public ListCommand(IllegalArgumentException e) {
        error = e;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (error != null) {
            return new CommandResult(MESSAGE_FAIL_CODE, Level.MODULE);
        }
        if (moduleCode != null && lectureName != null) {
            if (!model.hasLecture(moduleCode, lectureName)) {
                return new CommandResult(
                    String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, moduleCode, lectureName), Level.MODULE);
            }
            // Update filtered videos and show
            return new CommandResult(String.format(MESSAGE_SUCCESS_VIDEOS, moduleCode, lectureName), Level.VIDEO);
        }
        if (moduleCode != null) {
            if (!model.hasModule(moduleCode)) {
                return new CommandResult(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode), Level.LECTURE);
            }
            ReadOnlyModule module = model.getTracker().getModule(moduleCode);
            model.updateFilteredLectureList(new LecturePredicate(module), module);
            return new CommandResult(String.format(MESSAGE_SUCCESS_LECTURES, moduleCode), Level.LECTURE);
        }
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS_MODULES, Level.MODULE);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (other instanceof ListCommand) {
            ListCommand otherCommand = (ListCommand) other;
            if (moduleCode != null && otherCommand.moduleCode != null) {
                return moduleCode.equals(otherCommand.moduleCode);
            }
            if (lectureName != null && otherCommand.lectureName != null) {
                return lectureName.equals(otherCommand.lectureName);
            }
            return (moduleCode == null && lectureName == null)
                && (otherCommand.moduleCode == null && otherCommand.lectureName == null);
        }
        return false;
    }
}
