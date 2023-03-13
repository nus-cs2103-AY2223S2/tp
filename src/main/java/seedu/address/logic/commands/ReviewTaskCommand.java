package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

public class ReviewTaskCommand extends Command{
    public static final String COMMAND_WORD = "reviewtask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Review person/s assigned to a task."
            + "Parameters "
            + PREFIX_TASK_INDEX + "TASK INDEX ";

    public static final String MESSAGE_PERSON_ASSIGNED = "%1$s has been assigned to the following person/s:";
    public static final String MESSAGE_NO_PERSON_ASSIGNED = "%1$s has not been assigned to anyone.";

    private final Index taskIndex;

    /**
     * Creates ReviewTask object with given taskIndex
     */
    public ReviewTaskCommand(Index taskIndex) {
        requireAllNonNull(taskIndex);

        this.taskIndex = taskIndex;
    }

    /**
     * Executes ReviewTaskCommand
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        List<Task> taskList = officeConnectModel.getTaskModelManager().getFilteredItemList();
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task task = taskList.get(taskIndex.getZeroBased());
        Id tId = task.getId();
        Subject subject = task.getSubject();

        RepositoryModelManager<PersonTask> personTaskModelManager = officeConnectModel.getPersonTaskModelManager();
        ObservableList<PersonTask> assignedPersonList = personTaskModelManager
                .getFilteredItemList()
                .filtered(persontask -> persontask.getTaskId().equals(tId));

        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        model.updateFilteredPersonList(person -> assignedPersonList.stream()
                .anyMatch(personTask -> personTask.getPersonId().equals(person.getId())));
        taskModelManager.updateFilteredItemList(assignedtask -> assignedPersonList.stream()
                .anyMatch(personTask -> personTask.getTaskId().equals(assignedtask.getId())));

        if(assignedPersonList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_PERSON_ASSIGNED, subject));
        } else {
            return new CommandResult(String.format(MESSAGE_PERSON_ASSIGNED, subject));
        }
    }
}
