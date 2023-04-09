package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EMPLOYEES_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.CARL;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.ELLE;
import static seedu.address.testutil.TypicalEmployees.FIONA;
import static seedu.address.testutil.TypicalEmployees.GEORGE;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.NameContainsAllKeywordsPredicate;
import seedu.address.model.employee.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_NO_EMPLOYEES_FOUND);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_multipleFullKeywordsName_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multiplePartialKeywordsName_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("best el");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, GEORGE), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multipleFullKeywordsDepartment_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("sales finance");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, GEORGE), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multiplePartialKeywordsDepartment_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_NO_EMPLOYEES_FOUND);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("sale inance");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multipleKeywordsNameAndDepartment_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("er marketing");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE, FIONA), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_singlePartialKeyword_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("el");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_multiplePartialKeywordName_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareKeywordsPredicate("Da ER");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multipleFullKeywordsNameAsterisk_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_NO_EMPLOYEES_FOUND);
        NameContainsAllKeywordsPredicate predicate = prepareAllKeywordsPredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_multipleKeywordsNameAndDepartmentAsterisk_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 2);
        NameContainsAllKeywordsPredicate predicate = prepareAllKeywordsPredicate("er marketing");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredEmployeeList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareKeywordsPredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsAllKeywordsPredicate}.
     */
    private NameContainsAllKeywordsPredicate prepareAllKeywordsPredicate(String userInput) {
        return new NameContainsAllKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
