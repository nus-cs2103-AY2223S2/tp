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
import seedu.address.model.shared.Id;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

/**
 * Finds the list of all tasks whose subject contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "findt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the task based on given keyword. "
            + "Parameters: KEYWORD [KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " CS2103 TP";

    public static final String MESSAGE_TASK_FOUND_S = "%1$s task found";
    public static final String MESSAGE_TASK_FOUND_P = "%1$s tasks found";
    public static final String MESSAGE_NO_TASK_FOUND = "No such task found";

    private static final Logger logger = LogsCenter.getLogger(FindTaskCommand.class);

    private final TitleContainsKeywordsPredicate predicate;

    /**
     * Creates FindTaskCommand object with given taskIndex.
     */
    public FindTaskCommand(TitleContainsKeywordsPredicate predicate) {
        requireAllNonNull(predicate);

        this.predicate = predicate;
    }

    /**
     * Executes FindTaskCommand.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        List<Task> taskList = taskModelManager.filterReadOnlyList(predicate);

        List<Id> tIdList = taskList.stream().map(task->task.getId()).collect(Collectors.toList());

        ObservableList<AssignTask> assignedPersonList = getAssignedPersonList(officeConnectModel, tIdList);

        displayAssignedTasksAndPersons(model, taskModelManager, assignedPersonList, tIdList);

        if (taskList.isEmpty()) {
            logger.info("No tasks found");
            return new CommandResult(MESSAGE_NO_TASK_FOUND);
        } else {
            int numOfTaskFound = taskModelManager.getFilteredListSize();
            String message = numOfTaskFound > 1 ? MESSAGE_TASK_FOUND_P : MESSAGE_TASK_FOUND_S;
            return new CommandResult(String.format(message, numOfTaskFound));
        }
    }

    private static void displayAssignedTasksAndPersons(Model model, RepositoryModelManager<Task> taskModelManager,
                                                       ObservableList<AssignTask> assignedPersonList,
                                                       List<Id> tIdList) {
        model.updateFilteredPersonList(person -> assignedPersonList.stream()
                .anyMatch(assignTask -> assignTask.getPersonId().equals(person.getId())));
        taskModelManager.updateFilteredItemList(task ->
                tIdList.stream().anyMatch(id -> id.equals(task.getId())));
    }

    private static ObservableList<AssignTask> getAssignedPersonList(OfficeConnectModel officeConnectModel,
                                                                    List<Id> tIdList) {
        RepositoryModelManager<AssignTask> assignTaskModelManager = officeConnectModel.getAssignTaskModelManager();
        return assignTaskModelManager.filterItemList(assignTask ->
                tIdList.stream().anyMatch(id -> id.equals(assignTask.getTaskId())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && this.predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
