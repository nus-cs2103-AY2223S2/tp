package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskRepository;

import java.util.Arrays;
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
import seedu.address.model.task.TitleContainsExactKeywordsPredicate;


public class ReviewTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));
    private OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(new
            RepositoryModelManager<>(officeConnectModel.getTaskModelManager().getReadOnlyRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));

    @Test
    public void equals() {
        TitleContainsExactKeywordsPredicate firstPredicate =
                new TitleContainsExactKeywordsPredicate(Collections.singletonList("first"));
        TitleContainsExactKeywordsPredicate secondPredicate =
                new TitleContainsExactKeywordsPredicate(Collections.singletonList("second"));
        ReviewTaskCommand reviewFirstCommand = new ReviewTaskCommand(firstPredicate);
        ReviewTaskCommand reviewSecondCommand = new ReviewTaskCommand(secondPredicate);

        // same object -> returns true
        assertTrue(reviewFirstCommand.equals(reviewFirstCommand));

        // same values -> returns true
        ReviewTaskCommand reviewFirstCommandCopy = new ReviewTaskCommand(firstPredicate);
        assertTrue(reviewFirstCommand.equals(reviewFirstCommandCopy));

        // different types -> returns false
        assertFalse(reviewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reviewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(reviewFirstCommand.equals(reviewSecondCommand));
    }

    @Test
    public void execute_invalidKeywords_noTaskReviewed() {
        String expectedMessage = Messages.MESSAGE_INVALID_TASK;
        TitleContainsExactKeywordsPredicate predicate = preparePredicate("Project Destroy");
        ReviewTaskCommand command = new ReviewTaskCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);
        // Does not flush out the GUI when taking in invalid keywords
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
        assertEquals(expectedOfficeConnectModel.getTaskModelManager().getFilteredItemList(),
                officeConnectModel.getTaskModelManager().getFilteredItemList());

    }

    @Test
    public void execute_validKeywords_noPersonAssigned() {
        String expectedMessage = String.format(ReviewTaskCommand.MESSAGE_NO_PERSON_ASSIGNED, "Send email to client");
        TitleContainsExactKeywordsPredicate predicate = preparePredicate("Send email to client");
        ReviewTaskCommand command = new ReviewTaskCommand(predicate);
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

    private Id getAssignedTaskId(TitleContainsExactKeywordsPredicate predicate) {
        ObservableList<Task> taskList = officeConnectModel
                .getTaskModelManager()
                .getReadOnlyRepository().getData()
                .filtered(predicate);
        Id tId = taskList.get(0).getId();
        return tId;
    }

    private ObservableList<AssignTask> getAssignedPersonList(Id tId) {
        ObservableList<AssignTask> assignedPersonList = officeConnectModel.getAssignTaskModelManager()
                .getFilteredItemList()
                .filtered(persontask -> persontask.getTaskId().equals(tId));
        return assignedPersonList;
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsExactKeywordsPredicate}.
     */
    private TitleContainsExactKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsExactKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
