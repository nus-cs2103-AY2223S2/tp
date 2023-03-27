package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.task.logic.parser.CliSyntax.PREFIX_SUBTASK_INDEX;
import static seedu.task.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.Subtask;
import seedu.task.model.task.Task;

/**
 * Adds a task to the task book.
 */
public class DeleteSubtaskCommand extends Command {

    public static final String COMMAND_WORD = "remove-subsection";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a subsection to a certain task "
        + "by the index number used in the displayed task list.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_SUBTASK_INDEX + "INDEX] "
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_SUBTASK_INDEX + " 1 ";

    public static final String MESSAGE_SUCCESS = "Subsection deleted: %1$s";

    private final Index subtaskIndex;
    private final Index index;


    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public DeleteSubtaskCommand(Index index, Index subtaskIndex) {
        requireNonNull(index);
        requireNonNull(subtaskIndex);
        this.index = index;
        this.subtaskIndex = subtaskIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToEdit = lastShownList.get(index.getZeroBased());
        if (subtaskIndex.getZeroBased() < 0
            || subtaskIndex.getZeroBased() >= taskToEdit.getSubtasks().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Subtask taskToDelete = taskToEdit.getSubtasks().get(subtaskIndex.getZeroBased());
        Task newTask = createNewTask(taskToEdit, subtaskIndex);

        model.setTask(taskToEdit, newTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete));
    }

    /**
     * Create a new task to be used for updating the model
     * @param task The original task in the task list
     * @param subtaskIndex The index of the subtask to be deleted
     * @return A {@code Task}
     */
    public Task createNewTask(Task task, Index subtaskIndex) throws CommandException {
        Name name = task.getName();
        Description description = task.getDescription();
        Set<Tag> tags = task.getTags();
        Effort effort = task.getEffort();
        List<Subtask> subtasks = task.getSubtasks();
        if (subtaskIndex.getZeroBased() >= task.getSubtaskSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (task instanceof SimpleTask) {
            SimpleTask newSimpleTask = new SimpleTask(name, description, tags, effort, subtasks);
            newSimpleTask.removeSubtask(subtaskIndex);
            return newSimpleTask;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            Date from = event.getFrom();
            Date to = event.getTo();
            Event newEvent = new Event(name, description, tags, from, to, effort, subtasks);
            newEvent.removeSubtask(subtaskIndex);
            return newEvent;
        } else {
            Deadline deadline = (Deadline) task;
            Date deadlineDate = deadline.getDeadline();
            Deadline newDeadline = new Deadline(name, description, tags, deadlineDate, effort, subtasks);
            newDeadline.removeSubtask(subtaskIndex);
            return newDeadline;
        }
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteSubtaskCommand // instanceof handles nulls
            && this.index.equals(((DeleteSubtaskCommand) other).index)
            && this.subtaskIndex.equals(((DeleteSubtaskCommand) other).subtaskIndex));
    }

}
