package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.NameContainsInOrderKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Task;


/**
 * Finds all persons whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "findp";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds persons whose names contain any "
            + "of the given keywords. "
            + "Parameters: KEYWORD [KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " John Cena";

    public static final String MESSAGE_PERSON_FOUND_S = "%1$s person found";
    public static final String MESSAGE_PERSON_FOUND_P = "%1$s persons found";
    public static final String MESSAGE_NO_PERSON_FOUND = "No such person found";
    private static final Logger logger = LogsCenter.getLogger(FindCommand.class);
    private final NameContainsInOrderKeywordsPredicate predicate;


    /**
     * Creates FindCommand object with given personIndex.
     */
    public FindCommand(NameContainsInOrderKeywordsPredicate predicate) {
        requireAllNonNull(predicate);

        this.predicate = predicate;
    }

    /**
     * Executes FindCommand.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        List<Person> personList = model.filterReadOnlyPersonList(predicate);

        List<Id> pIdList = personList.stream().map(person -> person.getId()).collect(Collectors.toList());

        ObservableList<AssignTask> assignedTaskList = getAssignedTaskList(officeConnectModel, pIdList);

        displayAssignedTaskAndPerson(model, officeConnectModel, assignedTaskList, pIdList);

        if (personList.isEmpty()) {
            logger.info("No persons found");
            return new CommandResult(MESSAGE_NO_PERSON_FOUND);
        } else {
            int numOfPersonFound = model.getFilteredPersonList().size();
            String message = numOfPersonFound > 1 ? MESSAGE_PERSON_FOUND_P : MESSAGE_PERSON_FOUND_S;
            return new CommandResult(String.format(message, numOfPersonFound));
        }
    }

    private static void displayAssignedTaskAndPerson(Model model, OfficeConnectModel officeConnectModel,
                                                     ObservableList<AssignTask> assignedTaskList, List<Id> pIdList) {
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        model.updateFilteredPersonList(person ->
                pIdList.stream().anyMatch(id -> id.equals(person.getId())));
        taskModelManager.updateFilteredItemList(task -> assignedTaskList.stream()
                .anyMatch(personTask -> personTask.getTaskId().equals(task.getId())));
    }

    private static ObservableList<AssignTask> getAssignedTaskList(OfficeConnectModel officeConnectModel,
                                                                  List<Id> pIdList) {
        RepositoryModelManager<AssignTask> personTaskModelManager = officeConnectModel.getAssignTaskModelManager();
        return personTaskModelManager.filterItemList(assignTask ->
                pIdList.stream().anyMatch(id -> id.equals(assignTask.getPersonId())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && this.predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
