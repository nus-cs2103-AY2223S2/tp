package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;


/**
 * Marks a task as done using its displayed index from the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + PREFIX_TASK_INDEX + "1" + PREFIX_SCORE + "4";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s \nPerformance Score: %2$s";

    private final Index targetIndex;
    private final Score score;

    /**
     * Creates a MarkCommand to mark the specified {@code Task}
     * @param targetIndex
     * @param score
     */
    public MarkCommand(Index targetIndex, Score score) {
        this.targetIndex = targetIndex;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(model);
        requireNonNull(taskBookModel);
        List<Task> lastShownList = taskBookModel.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        if (score.getValue() < 0 || score.getValue() > 5) {
            throw new CommandException(Score.MESSAGE_CONSTRAINTS);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        taskBookModel.markTask(taskToMark, score);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToMark, score.toString()));
    }
}
