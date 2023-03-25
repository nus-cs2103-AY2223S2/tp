package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordsPredicate;


public class FindTaskCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));
    private final OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(new
            RepositoryModelManager<>(officeConnectModel.getTaskModelManager().getReadOnlyRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate("first");
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate("second");
        FindTaskCommand findFirstCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different predicate -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_invalidKeywords_noTaskFound() {
        String expectedMessage = Messages.MESSAGE_INVALID_TASK;
        TitleContainsKeywordsPredicate predicate = preparePredicate("Project Destroy");
        FindTaskCommand command = new FindTaskCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);
        // Does not flush out the GUI when taking in invalid keywords
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
        assertEquals(expectedOfficeConnectModel.getTaskModelManager().getFilteredItemList(),
                officeConnectModel.getTaskModelManager().getFilteredItemList());

    }

    @Test
    public void execute_validKeywords_noPersonAssigned() {
        String expectedMessage = String.format(FindTaskCommand.MESSAGE_NO_TASK_FOUND, "Send email to client");
        TitleContainsKeywordsPredicate predicate = preparePredicate("Send email to client");
        FindTaskCommand command = new FindTaskCommand(predicate);
        Id tId = getAssignedTaskId(predicate);

        ObservableList<AssignTask> assignedPersonList = getAssignedPersonList(tId);
        assertEquals(Collections.emptyList(), assignedPersonList);

        expectedModel.updateFilteredPersonList(person -> assignedPersonList.stream()
                .anyMatch(personTask -> personTask.getPersonId().equals(person.getId())));
        expectedOfficeConnectModel.getTaskModelManager()
                .updateFilteredItemList(task -> task.getId().equals(tId));
        // Person list expected to be empty
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                officeConnectModel, expectedOfficeConnectModel);
        // Task list expected to be updated accordingly
        assertTaskCommandSuccess(command, officeConnectModel, expectedMessage, expectedOfficeConnectModel);
    }

    private Id getAssignedTaskId(TitleContainsKeywordsPredicate predicate) {
        ObservableList<Task> taskList = officeConnectModel
                .getTaskModelManager()
                .getReadOnlyRepository().getData()
                .filtered(predicate);
        return taskList.get(0).getId();
    }

    private ObservableList<AssignTask> getAssignedPersonList(Id tId) {
        return officeConnectModel.getAssignTaskModelManager()
                .getFilteredItemList()
                .filtered(persontask -> persontask.getTaskId().equals(tId));
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(userInput);
    }
}
