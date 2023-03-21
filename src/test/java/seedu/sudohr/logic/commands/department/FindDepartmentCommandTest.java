package seedu.sudohr.logic.commands.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.commons.core.Messages.MESSAGE_DEPARTMENT_LISTED_OVERVIEW;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.DepartmentNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindDepartmentCommandTest {
    private Model model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    @Test
    public void equals() {
        DepartmentNameContainsKeywordsPredicate firstPredicate =
                new DepartmentNameContainsKeywordsPredicate(Collections.singletonList("first"));
        DepartmentNameContainsKeywordsPredicate secondPredicate =
                new DepartmentNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindDepartmentCommand findFirstCommand = new FindDepartmentCommand(firstPredicate);
        FindDepartmentCommand findSecondCommand = new FindDepartmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDepartmentCommand findFirstCommandCopy = new FindDepartmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different department -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_DEPARTMENT_LISTED_OVERVIEW, 0);
        DepartmentNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindDepartmentCommand command = new FindDepartmentCommand(predicate);
        expectedModel.updateFilteredDepartmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDepartmentList());
    }

    @Test
    public void execute_multipleKeywords_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_DEPARTMENT_LISTED_OVERVIEW, 2);
        DepartmentNameContainsKeywordsPredicate predicate = preparePredicate("Human Engineering");
        FindDepartmentCommand command = new FindDepartmentCommand(predicate);
        expectedModel.updateFilteredDepartmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HUMAN_RESOURCES, ENGINEERING), model.getFilteredDepartmentList());
    }

    /**
     * Parses {@code userInput} into a {@code DepartmentNameContainsKeywordsPredicate}.
     */
    private DepartmentNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DepartmentNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
