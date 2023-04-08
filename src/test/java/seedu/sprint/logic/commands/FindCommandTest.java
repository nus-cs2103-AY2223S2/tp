package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.commons.core.Messages.MESSAGE_APPLICATIONS_LISTED_OVERVIEW;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.testutil.TypicalApplications.AMAZON;
import static seedu.sprint.testutil.TypicalApplications.GOOGLE;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.parser.Prefix;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Application Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(
            getTypicalInternshipBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(
            getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        ApplicationContainsKeywordsPredicate firstPredicate =
                new ApplicationContainsKeywordsPredicate(Collections.singletonList("first"));
        ApplicationContainsKeywordsPredicate secondPredicate =
                new ApplicationContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }


    @Test
    public void execute_invalidKeywords_noApplicationFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATIONS_LISTED_OVERVIEW, 0);
        ApplicationContainsKeywordsPredicate predicate = preparePredicate("?");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedApplicationList());
    }

    @Test
    public void execute_oneKeyword_oneApplicationFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATIONS_LISTED_OVERVIEW, 1);
        ApplicationContainsKeywordsPredicate predicate = preparePredicate("Google");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GOOGLE), model.getSortedApplicationList());
    }

    @Test
    public void execute_multiplePrefixes_oneApplicationFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATIONS_LISTED_OVERVIEW, 1);
        List<String> role = Arrays.asList("Intern");
        List<String> companyName = Arrays.asList("Amazon");
        List<String> status = Arrays.asList("applied");
        HashMap<Prefix, List<String>> multiplePrefixesMap = new HashMap<>() {
            {
                put(PREFIX_ROLE, role);
                put(PREFIX_COMPANY_NAME, companyName);
                put(PREFIX_STATUS, status);
            }
        };
        ApplicationContainsKeywordsPredicate predicate =
                new ApplicationContainsKeywordsPredicate(multiplePrefixesMap);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMAZON), model.getSortedApplicationList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ApplicationContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ApplicationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
