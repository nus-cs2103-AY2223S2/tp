package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;


/**
 * Unmarks a task as not done using its displayed index from the address book.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the index number in the displayed task list as not done.\n"
            + "Parameters: " + PREFIX_TASK_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TASK_INDEX + "1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Unmarked Task: %1$s";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(model);
        requireNonNull(taskBookModel);
        List<Task> lastShownList = taskBookModel.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Task unmarkedTask = createUnmarkedTask(taskToUnmark);
        taskBookModel.unmarkTask(taskToUnmark, unmarkedTask, targetIndex);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    private static Task createUnmarkedTask(Task taskToUnmark) {
        assert taskToUnmark != null;
        TaskDescription taskDesc = taskToUnmark.getDescription();
        String taskDate = taskToUnmark.getDate().toString();
        String taskType = taskToUnmark.getTaskType();
        Task unmarkedTask = new Task(taskDesc, taskDate, taskType);
        String personAssignedName = taskToUnmark.getPersonAssignedName();
        String personAssignedRole = taskToUnmark.getPersonAssignedRole();
        Index personToAssign = taskToUnmark.getPersonAssignedIndex();
        unmarkedTask.assignPerson(personToAssign, personAssignedName, personAssignedRole);
        boolean status = false;
        unmarkedTask.setStatus(status);
        unmarkedTask.setScore(null);
        Comment comment = taskToUnmark.getTaskComment();
        unmarkedTask.setTaskComment(comment);
        return unmarkedTask;
    }
}
