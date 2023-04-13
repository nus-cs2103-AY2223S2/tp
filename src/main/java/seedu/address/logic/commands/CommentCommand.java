package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Adds a CommentTask to the address book.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment to a specific task "
            + "Parameters: "
            + PREFIX_TASK_INDEX + "TASK_INDEX "
            + PREFIX_COMMENT + "COMMENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + "1 "
            + PREFIX_COMMENT + "Joe has accomplished this task exceptionally well";

    public static final String MESSAGE_SUCCESS = "New comment added: %1$s to Task: %2$s \n ";

    private final Comment toAddComment;
    private final Index toReceiveComment;

    /**
     * Creates an AddCommand to add the specified {@code DeadlineTask}
     */
    public CommentCommand(Index taskIndex, Comment comment) {
        requireNonNull(taskIndex);
        requireNonNull(comment);
        toAddComment = comment;
        toReceiveComment = taskIndex;
    }

    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(model);
        requireNonNull(taskBookModel);
        List<Task> lastShownList = taskBookModel.getFilteredTaskList();

        if (!taskBookModel.hasTaskIndex(toReceiveComment)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToComment = lastShownList.get(toReceiveComment.getZeroBased());
        String taskString = taskToComment.toString();

        Task commentedTask = createCommentedTask(taskToComment, toAddComment);
        taskBookModel.commentOnTask(taskToComment, commentedTask, toReceiveComment);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddComment, taskString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommentCommand // instanceof handles nulls
                && toAddComment.equals(((CommentCommand) other).toAddComment)
                && toReceiveComment.equals(((CommentCommand) other).toReceiveComment));
    }

    private static Task createCommentedTask(Task taskToComment, Comment comment) {
        assert taskToComment != null;
        TaskDescription taskDesc = taskToComment.getDescription();
        String taskDate = taskToComment.getDate().toString();
        String taskType = taskToComment.getTaskType();
        Task commentedTask = new Task(taskDesc, taskDate, taskType);
        String personAssignedName = taskToComment.getPersonAssignedName();
        String personAssignedRole = taskToComment.getPersonAssignedRole();
        Index personToAssign = taskToComment.getPersonAssignedIndex();
        commentedTask.assignPerson(personToAssign, personAssignedName, personAssignedRole);
        boolean status = taskToComment.isDone();
        commentedTask.setStatus(status);
        commentedTask.setTaskComment(comment);
        Score score = taskToComment.getScore();
        commentedTask.setScore(score);
        return commentedTask;
    }
}
