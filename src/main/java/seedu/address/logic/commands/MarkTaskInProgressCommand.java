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
 * Marks a task identified by its displayed index from a specified student's task list as in progress.
 */
public class MarkTaskInProgressCommand extends Command {

    public static final String COMMAND_WORD = "markinprogress";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by its index number in the task list of the "
            + "student specified as in progress.\n"
            + "Parameters: INDEX_OF_STUDENT INDEX_OF_TASK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_MARK_INPROGRESS_SUCCESS = "Marked task as in progress for %1$s: %2$s";

    private final Index studentIndex;
    private final Index taskIndex;

    /**
     * Creates an MarkTaskInProgressCommand to mark the specified task of
     * a specified student as in progress
     */
    public MarkTaskInProgressCommand(Index studentIndex, Index taskIndex) {
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

        Student studentToMarkTask = lastShownList.get(studentIndex.getZeroBased());
        List<Task> studentTaskList = studentToMarkTask.getFilteredTaskList();

        if (taskIndex.getZeroBased() >= studentTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = studentTaskList.get(taskIndex.getZeroBased());
        studentToMarkTask.markTaskAsInProgress(taskToMark);

        return new CommandResult(String.format(MESSAGE_MARK_INPROGRESS_SUCCESS,
                studentToMarkTask.getName(), taskToMark.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskInProgressCommand // instanceof handles nulls
                && studentIndex.equals(((MarkTaskInProgressCommand) other).studentIndex)
                && taskIndex.equals(((MarkTaskInProgressCommand) other).taskIndex)); // state check;
    }
}
