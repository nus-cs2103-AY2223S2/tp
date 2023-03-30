package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;


/**
 * Adds a task to a student identified using it's displayed index in the student list.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the student identified by the index number in the displayed student list.\n"
            + "Parameters: INDEX_OF_STUDENT (must be a positive integer) "
            + PREFIX_TASK_TITLE + "TASK_TITLE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_TITLE + "Complete E Math Paper 1";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added task for %1$s: %2$s";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in this student's task list";

    private final Index targetIndex;
    private final Task taskToAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to
     * a student
     */
    public AddTaskCommand(Index targetIndex, Task taskToAdd) {
        requireAllNonNull(targetIndex, taskToAdd);
        this.targetIndex = targetIndex;
        this.taskToAdd = taskToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddTaskTo = lastShownList.get(targetIndex.getZeroBased());

        if (studentToAddTaskTo.hasTask(taskToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        studentToAddTaskTo.addTask(taskToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS,
                studentToAddTaskTo.getName(), taskToAdd.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskToAdd.equals(((AddTaskCommand) other).taskToAdd)
                && targetIndex.equals(((AddTaskCommand) other).targetIndex)); // state check;
    }
}
