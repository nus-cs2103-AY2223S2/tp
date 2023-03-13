package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Task;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;

/**
 * Reviews the list of tasks assigned to a specific person
 */
public class ReviewCommand extends Command {
    public static final String COMMAND_WORD = "review";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Review tasks assigned to a person."
            + "Parameters "
            + PREFIX_PERSON_INDEX + "PERSON INDEX ";

    public static final String MESSAGE_TASK_ASSIGNED = "%1$s has been assigned to the following tasks:";
    public static final String MESSAGE_NO_TASK_ASSIGNED = "%1$s does not have any assigned tasks.";

    private final Index personIndex;

    /**
     * Creates Review object with given personIndex
     */
    public ReviewCommand(Index personIndex) {
        requireAllNonNull(personIndex);

        this.personIndex = personIndex;
    }

    /**
     * Executes ReviewCommand
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        List<Person> personList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = personList.get(personIndex.getZeroBased());
        Id pId = person.getId();
        Name name = person.getName();

        ObservableList<PersonTask> assignedTaskList = getAssignedTaskList(officeConnectModel, pId);

        displayAssignedTaskAndPerson(model, officeConnectModel, assignedTaskList);

        if(assignedTaskList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_TASK_ASSIGNED, name));
        } else {
            return new CommandResult(String.format(MESSAGE_TASK_ASSIGNED, name));
        }
    }

    private static void displayAssignedTaskAndPerson(Model model, OfficeConnectModel officeConnectModel, ObservableList<PersonTask> assignedTaskList) {
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        model.updateFilteredPersonList(assignedPerson -> assignedTaskList.stream()
                .anyMatch(personTask -> personTask.getPersonId().equals(assignedPerson.getId())));
        taskModelManager.updateFilteredItemList(task -> assignedTaskList.stream()
                .anyMatch(personTask -> personTask.getTaskId().equals(task.getId())));
    }

    private static ObservableList<PersonTask> getAssignedTaskList(OfficeConnectModel officeConnectModel, Id pId) {
        RepositoryModelManager<PersonTask> personTaskModelManager = officeConnectModel.getPersonTaskModelManager();
        ObservableList<PersonTask> assignedTaskList = personTaskModelManager
                .getFilteredItemList()
                .filtered(persontask -> persontask.getPersonId().equals(pId));
        return assignedTaskList;
    }
}
