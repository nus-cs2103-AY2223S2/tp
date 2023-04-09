package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index personIndex;

    public DeleteCommand(Index personIndex) {
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = taskBookModel.getFilteredTaskList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(personIndex.getZeroBased());
        taskBookModel.deletePersonFromTask(personIndex);
        model.deletePerson(personToDelete);

        for (Task task : lastShownTaskList) {
            Task updatedTask = createUpdatedTask(task);
            taskBookModel.setTask(task, updatedTask);
        }

        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);


        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createUpdatedTask(Task task) throws CommandException {
        assert task != null;
        
        Task updatedTask = new Task(task.getDescription(), task.getDate(), task.getTaskType());
        updatedTask.assignPerson(task.getPersonAssignedIndex(), task.getPersonAssignedName(), task.getPersonAssignedRole());
        updatedTask.setScore(task.getScore());
        updatedTask.setTaskComment(task.getTaskComment());
        updatedTask.setStatus(task.isDone());

        return updatedTask;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && personIndex.equals(((DeleteCommand) other).personIndex)); // state check
    }
}
