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
 * Marks a task identified by its displayed index from a specified student's task list as complete.
 */
public class MarkTaskCompleteCommand extends Command {

    public static final String COMMAND_WORD = "markcomplete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by its index number in the task list of the "
            + "student specified as complete.\n"
            + "Parameters: INDEX_OF_STUDENT INDEX_OF_TASK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_MARK_COMPLETE_SUCCESS = "Marked task as complete for %1$s: %2$s";

    private final Index studentIndex;
    private final Index taskIndex;

    /**
     * Creates an MarkTaskCompleteCommand to mark the specified task of
     * a specified student as complete
     */
    public MarkTaskCompleteCommand(Index studentIndex, Index taskIndex) {
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
        studentToMarkTask.markTaskAsComplete(taskToMark);

        return new CommandResult(String.format(MESSAGE_MARK_COMPLETE_SUCCESS,
                studentToMarkTask.getName(), taskToMark.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCompleteCommand // instanceof handles nulls
                && studentIndex.equals(((MarkTaskCompleteCommand) other).studentIndex)
                && taskIndex.equals(((MarkTaskCompleteCommand) other).taskIndex)); // state check;
    }
}
