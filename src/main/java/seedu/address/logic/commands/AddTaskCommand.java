package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Adds a task to a person identified using it's displayed index in the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the person identified by the index number in the displayed address book.\n"
            + " Parameters: INDEX_OF_PERSON (must be positive integers) "
            + PREFIX_TASK_TITLE + "TASK_TITLE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_TITLE + "Complete E Math Paper 1";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added for %1$s: %2$s";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in this student's task list";

    private final Index targetIndex;
    private final Task taskToAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to
     * a person
     */
    public AddTaskCommand(Index targetIndex, Task taskToAdd) {
        requireNonNull(taskToAdd);
        this.targetIndex = targetIndex;
        this.taskToAdd = taskToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddTaskTo = lastShownList.get(targetIndex.getZeroBased());

        if (personToAddTaskTo.hasTask(taskToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        personToAddTaskTo.addTask(taskToAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS,
                personToAddTaskTo.getName(), taskToAdd.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskToAdd.equals(((AddTaskCommand) other).taskToAdd)
                && targetIndex.equals(((AddTaskCommand) other).targetIndex)); // state check;
    }
}
