package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;


/**
 * Deletes a task identified by its displayed index from a specified student's task list.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the task list of the "
            + "student specified.\n"
            + "Parameters: INDEX_OF_STUDENT INDEX_OF_TASK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2 3";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task for %1$s: %2$s";

    private final Index studentIndex;
    private final Index taskIndex;

    /**
     * Creates an DeleteTaskCommand to delete the specified task from
     * a specified student
     */
    public DeleteTaskCommand(Index studentIndex, Index taskIndex) {
        requireAllNonNull(studentIndex, taskIndex);
        this.taskIndex = taskIndex;
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDeleteTask = lastShownList.get(studentIndex.getZeroBased());
        List<Task> studentTaskList = studentToDeleteTask.getFilteredTaskList();

        if (taskIndex.getZeroBased() >= studentTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = studentTaskList.get(taskIndex.getZeroBased());
        studentToDeleteTask.removeTask(taskToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                studentToDeleteTask.getName(), taskToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && studentIndex.equals(((DeleteTaskCommand) other).studentIndex)
                && taskIndex.equals(((DeleteTaskCommand) other).taskIndex)); // state check;
    }
}
