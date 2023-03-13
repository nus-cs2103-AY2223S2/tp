package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Subject;
import seedu.address.model.task.SubjectContainsExactKeywordsPredicate;
import seedu.address.model.task.Task;

/**
 * Review the list of persons assigned to a specified task.
 */
public class ReviewTaskCommand extends Command {
    public static final String COMMAND_WORD = "reviewtask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Review person/s assigned to a task.\n"
            + "Parameters: TASK_NAME \n"
            + "Example: " + COMMAND_WORD + " CS2103 TP";

    public static final String MESSAGE_PERSON_ASSIGNED = "%1$s has been assigned to the following person/s:";
    public static final String MESSAGE_NO_PERSON_ASSIGNED = "%1$s has not been assigned to anyone.";

    private final SubjectContainsExactKeywordsPredicate predicate;

    /**
     * Creates ReviewTask object with given taskIndex
     */
    public ReviewTaskCommand(SubjectContainsExactKeywordsPredicate predicate) {
        requireAllNonNull(predicate);

        this.predicate = predicate;
    }

    /**
     * Executes ReviewTaskCommand
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        ObservableList<Task> taskList = officeConnectModel.getTaskModelManager()
                .getFilteredItemList()
                .filtered(predicate);
        if (taskList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK);
        }
        Task task = taskList.get(0);
        Id tId = task.getId();
        Subject subject = task.getSubject();

        ObservableList<PersonTask> assignedPersonList = getAssignedPersonList(officeConnectModel, tId);

        displayAssignedTasksAndPersons(model, officeConnectModel, assignedPersonList);

        if (assignedPersonList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_PERSON_ASSIGNED, subject));
        } else {
            return new CommandResult(String.format(MESSAGE_PERSON_ASSIGNED, subject));
        }
    }

    private static void displayAssignedTasksAndPersons(Model model, OfficeConnectModel officeConnectModel,
                                                       ObservableList<PersonTask> assignedPersonList) {
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        model.updateFilteredPersonList(person -> assignedPersonList.stream()
                .anyMatch(personTask -> personTask.getPersonId().equals(person.getId())));
        taskModelManager.updateFilteredItemList(assignedtask -> assignedPersonList.stream()
                .anyMatch(personTask -> personTask.getTaskId().equals(assignedtask.getId())));
    }

    private static ObservableList<PersonTask> getAssignedPersonList(OfficeConnectModel officeConnectModel, Id tId) {
        RepositoryModelManager<PersonTask> personTaskModelManager = officeConnectModel.getPersonTaskModelManager();
        ObservableList<PersonTask> assignedPersonList = personTaskModelManager
                .getFilteredItemList()
                .filtered(persontask -> persontask.getTaskId().equals(tId));
        return assignedPersonList;
    }
}
