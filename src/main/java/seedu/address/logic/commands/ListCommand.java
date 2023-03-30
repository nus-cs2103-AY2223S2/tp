package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DisplayListLevel;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.LecturePredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.VideoPredicate;


/**
 * Lists all modules/lectures/videos in the tracker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "Make sure that you are calling this command from the correct context.\n"
            + COMMAND_WORD + " " + PREFIX_ROOT + ": List modules from ANY context.\n"
            + COMMAND_WORD + ": List modules or lectures or videos depending on current context.\n"
            + COMMAND_WORD + " " + PREFIX_MODULE + " {module_code}: List lectures from MODULE context.\n"
            + COMMAND_WORD + " " + PREFIX_LECTURE + " {lecture_name}: List videos from LECTURE context.\n"
            + COMMAND_WORD + " " + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name}: List videos from ANY context.\n"
            + "Example: " + COMMAND_WORD + " | "
            + COMMAND_WORD + " " + PREFIX_MODULE + " CS2040S | "
            + COMMAND_WORD + " " + PREFIX_LECTURE + " Week 1\n"
            + COMMAND_WORD + " " + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 1";

    public static final String MESSAGE_SUCCESS_MODULES = "Listed all modules";

    public static final String MESSAGE_SUCCESS_LECTURES = "Listed all lectures from module: %s";

    public static final String MESSAGE_SUCCESS_VIDEOS = "Listed all videos from module: %s, lecture: %s";

    public static final String MESSAGE_FAIL_CODE = "Module code format is invalid";

    private final boolean isRoot;

    private ModuleCode moduleCode;

    private LectureName lectureName;

    /**
     * Creates a ListCommand to list content from current context
     */
    public ListCommand() {
        this.isRoot = false;
    }

    /**
     * Creates a ListCommand to list module contents from any context
     */
    public ListCommand(boolean isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * Creates a ListCommand to list lecture contents from module context
     * @param moduleCode
     */
    public ListCommand(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
        this.isRoot = false;
    }

    /**
     * Creates a ListCommand to list video contents from lecture context
     * @param moduleCode
     * @param lectureName
     */
    public ListCommand(ModuleCode moduleCode, LectureName lectureName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        this.isRoot = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isRoot) {
            return filterByModuleList(model);
        }

        if (moduleCode != null && lectureName != null) {
            if (!model.hasLecture(moduleCode, lectureName)) {
                throw new CommandException(
                    String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
            }
            return filterByVideoList(model);
        }
        if (moduleCode != null) {
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(
                    String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
            }
            return filterByLectureList(model);
        }
        return filterByModuleList(model);
    }

    private CommandResult filterByVideoList(Model model) {
        ReadOnlyLecture lecture = model.getLecture(moduleCode, lectureName);
        model.updateFilteredVideoList(new VideoPredicate(lecture), moduleCode, lecture);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS_VIDEOS, moduleCode, lectureName), DisplayListLevel.VIDEO);
    }

    private CommandResult filterByLectureList(Model model) {
        ReadOnlyModule module = model.getModule(moduleCode);
        model.updateFilteredLectureList(new LecturePredicate(module), module);
        return new CommandResult(String.format(MESSAGE_SUCCESS_LECTURES, moduleCode), DisplayListLevel.LECTURE);
    }

    private CommandResult filterByModuleList(Model model) {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS_MODULES, DisplayListLevel.MODULE);
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
