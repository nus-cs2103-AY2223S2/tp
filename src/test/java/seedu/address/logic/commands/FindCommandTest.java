package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;

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
import seedu.address.model.person.NameContainsInOrderKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Id;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
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
        NameContainsInOrderKeywordsPredicate firstPredicate =
                new NameContainsInOrderKeywordsPredicate("first");
        NameContainsInOrderKeywordsPredicate secondPredicate =
                new NameContainsInOrderKeywordsPredicate("second");
        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different predicate -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_invalidKeywords_noPersonFound() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON;
        NameContainsInOrderKeywordsPredicate predicate = preparePredicate("invalidKeyword");
        FindCommand command = new FindCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);
        // Does not flush out the GUI when taking in invalid keywords
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
        assertEquals(expectedOfficeConnectModel.getTaskModelManager().getFilteredItemList(),
                officeConnectModel.getTaskModelManager().getFilteredItemList());
    }
    @Test
    public void execute_validKeywords_noTaskAssigned() {
        String expectedMessage = String.format(FindCommand.MESSAGE_NO_PERSON_FOUND, "Alice Pauline");
        NameContainsInOrderKeywordsPredicate predicate = preparePredicate("Alice Pauline");
        FindCommand command = new FindCommand(predicate);

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

    private Id getAssignedPersonId(NameContainsInOrderKeywordsPredicate predicate) {
        List<Person> personList = model.getAddressBook()
                .getPersonList()
                .filtered(predicate);
        return personList.get(0).getId();
    }

    private ObservableList<AssignTask> getAssignedTaskList(Id pId) {
        return expectedOfficeConnectModel.getAssignTaskModelManager()
                .getFilteredItemList()
                .filtered(persontask -> persontask.getPersonId().equals(pId));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsInOrderKeywordsPredicate}.
     */
    private NameContainsInOrderKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsInOrderKeywordsPredicate(userInput);
    }

}
