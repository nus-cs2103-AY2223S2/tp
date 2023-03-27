package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
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
import seedu.task.model.task.exceptions.DuplicateTaskException;

/**
 * Adds a task to the task book.
 */
public class AddSubtaskCommand extends Command {

    public static final String COMMAND_WORD = "subsection";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a subsection to a certain task "
        + "by the index number used in the displayed task list.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION](Optional)\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "do lab "
        + PREFIX_DESCRIPTION + "lab worksheet needs to be printed";

    public static final String MESSAGE_SUCCESS = "New subsection added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This subsection already exists in the task";

    private final Subtask subtask;
    private final Index index;


    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddSubtaskCommand(Index index, Subtask subtask) {
        requireNonNull(index);
        requireNonNull(subtask);
        this.index = index;
        this.subtask = subtask;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()
            || index.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task newTask = createNewTask(taskToEdit, subtask);

        model.setTask(taskToEdit, newTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, subtask));
    }

    /**
     * Create a new task to be used for updating the model
     * @param task The original task in the task list
     * @param subtask The modified task after adding the subtask inside
     * @return A {@code Task}
     */
    public Task createNewTask(Task task, Subtask subtask) throws CommandException {
        Name name = task.getName();
        Description description = task.getDescription();
        Set<Tag> tags = task.getTags();
        Effort effort = task.getEffort();
        List<Subtask> subtasks = task.getSubtasks();
        if (task instanceof SimpleTask) {
            SimpleTask newSimpleTask = new SimpleTask(name, description, tags, effort, subtasks);
            try {
                newSimpleTask.addSubtask(subtask);
            } catch (DuplicateTaskException e) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }
            return newSimpleTask;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            Date from = event.getFrom();
            Date to = event.getTo();
            Event newEvent = new Event(name, description, tags, from, to, effort, subtasks);
            try {
                newEvent.addSubtask(subtask);
            } catch (DuplicateTaskException e) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }
            return newEvent;

        } else {
            Deadline deadline = (Deadline) task;
            Date deadlineDate = deadline.getDeadline();
            Deadline newDeadline = new Deadline(name, description, tags, deadlineDate, effort, subtasks);
            try {
                newDeadline.addSubtask(subtask);
            } catch (DuplicateTaskException e) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }
            return newDeadline;
        }
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddSubtaskCommand // instanceof handles nulls
            && subtask.equals(((AddSubtaskCommand) other).subtask));
    }

}
