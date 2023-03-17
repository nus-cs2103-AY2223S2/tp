package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.Teacher;


/**
 * Adds the name of a teacher into the module tracker
 */
public class TeacherCommand extends Command {

    public static final String COMMAND_WORD = "teacher";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the teacher of the module identified "
            + "by the index number. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "s/ [TEACHER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "s/ Prof Damyth \n"
            + "NOTE: s represents the Japanese term 'sensei'.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Deadline: %2$s";
    public static final String MESSAGE_ADD_TEACHER_SUCCESS = "Added teacher to Module: %1$s";
    public static final String MESSAGE_DELETE_TEACHER_SUCCESS = "Removed teacher from Module: %1$s";

    private final Index index;
    private final Teacher teacher;

    /**
     * Constructor for the teacher command.
     * @param index The index of the module to edit the teacher.
     * @param teacher The name of the teacher to be added.
     */
    public TeacherCommand(Index index, Teacher teacher) {
        requireAllNonNull(index, teacher);
        this.index = index;
        this.teacher = teacher;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = new Module(
                moduleToEdit.getName(), moduleToEdit.getType(), moduleToEdit.getTimeSlot(),
                moduleToEdit.getAddress(), moduleToEdit.getTags(), moduleToEdit.getRemark(),
                moduleToEdit.getDeadline(), teacher);

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        return new CommandResult(generateSuccessMessage(editedModule));
    }

    /**
     * Generates a command execution success message based on whether
     * the teacher is added to or removed from
     * {@code moduleToEdit}.
     */
    private String generateSuccessMessage(Module moduleToEdit) {
        String message = !teacher.value.isEmpty() ? MESSAGE_ADD_TEACHER_SUCCESS : MESSAGE_DELETE_TEACHER_SUCCESS;
        return String.format(message, moduleToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        // state check
        TeacherCommand e = (TeacherCommand) other;
        return index.equals(e.index)
                && teacher.equals(e.teacher);
    }
}
