package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import seedu.address.model.person.NameContainsExactKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Id;

/**
 * Contains integration tests (interaction with the Model) for {@code ReviewCommand}.
 */
public class ReviewCommandTest {
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
        NameContainsExactKeywordsPredicate firstPredicate =
                new NameContainsExactKeywordsPredicate(Collections.singletonList("first"));
        NameContainsExactKeywordsPredicate secondPredicate =
                new NameContainsExactKeywordsPredicate(Collections.singletonList("second"));
        ReviewCommand reviewFirstCommand = new ReviewCommand(firstPredicate);
        ReviewCommand reviewSecondCommand = new ReviewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(reviewFirstCommand.equals(reviewFirstCommand));

        // same values -> returns true
        ReviewCommand reviewFirstCommandCopy = new ReviewCommand(firstPredicate);
        assertTrue(reviewFirstCommand.equals(reviewFirstCommandCopy));

        // different types -> returns false
        assertFalse(reviewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reviewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(reviewFirstCommand.equals(reviewSecondCommand));
    }

    @Test
    public void execute_invalidKeywords_noPersonReviewed() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON;
        NameContainsExactKeywordsPredicate predicate = preparePredicate("invalidKeyword");
        ReviewCommand command = new ReviewCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);
        // Does not flush out the GUI when taking in invalid keywords
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
        assertEquals(expectedOfficeConnectModel.getTaskModelManager().getFilteredItemList(),
                officeConnectModel.getTaskModelManager().getFilteredItemList());
    }
    @Test
    public void execute_validKeywords_noTaskAssigned() {
        String expectedMessage = String.format(ReviewCommand.MESSAGE_NO_TASK_ASSIGNED, "Alice Pauline");
        NameContainsExactKeywordsPredicate predicate = preparePredicate("Alice Pauline");
        ReviewCommand command = new ReviewCommand(predicate);

        Id pId = getAssignedPersonId(predicate);

        ObservableList<AssignTask> assignedTaskList = getAssignedTaskList(pId);
        assertEquals(Collections.emptyList(), assignedTaskList);

        expectedModel.updateFilteredPersonList(person -> person.getId().equals(pId));
        expectedOfficeConnectModel.getTaskModelManager()
                .updateFilteredItemList(assignedPerson -> assignedTaskList.stream()
                .anyMatch(personTask -> personTask.getPersonId().equals(assignedPerson.getId())));
        // Person list should be updated accordingly
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // Task list expected to be empty
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                officeConnectModel, expectedOfficeConnectModel);
    }

    private Id getAssignedPersonId(NameContainsExactKeywordsPredicate predicate) {
        List<Person> personList = model.getAddressBook()
                .getPersonList()
                .filtered(predicate);
        Id pId = personList.get(0).getId();
        return pId;
    }

    private ObservableList<AssignTask> getAssignedTaskList(Id pId) {
        ObservableList<AssignTask> assignedTaskList = expectedOfficeConnectModel.getAssignTaskModelManager()
                .getFilteredItemList()
                .filtered(persontask -> persontask.getPersonId().equals(pId));
        return assignedTaskList;
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsExactKeywordsPredicate}.
     */
    private NameContainsExactKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsExactKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
